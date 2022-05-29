package kr.co.hlm.system.helmet;

import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.helmetstate.HelmetStateMapper;
import kr.co.hlm.system.helmetstate.HelmetStateServiceImpl;
import kr.co.hlm.system.kickboard.Kickboard;
import kr.co.hlm.system.kickboard.KickboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HelmetServiceImpl implements HelmetService{
    private final HelmetMapper helmetMapper;
    private final HelmetStateMapper helmetStateMapper;
    private final KickboardMapper kickboardMapper;
    public static Map<String, String> helmetPair = new HashMap<>();

    @Override
    public void createHelmet(Helmet helmet) {
        Kickboard pairKickboard = new Kickboard();
        pairKickboard.setIp(helmet.getKickboardIp());
        helmetPair.put(helmet.getNo(), kickboardMapper.select(pairKickboard).getNo());

        helmetMapper.insert(helmet);
    }

    //문서 추가
    @Override
    public List<Mark> getMarks(Helmet helmet) {
        List<Mark> marks = new ArrayList<>();
        List<Helmet> helmets = helmetMapper.selectAll(helmet);

        HelmetState helmetState = new HelmetState();
        for (Helmet setHelmet : helmets) {
            helmetState.setHelmetNo(setHelmet.getNo());
            helmetState = helmetStateMapper.select(helmetState);

            if (helmetState == null) {
                helmetState = new HelmetState();

                continue;
            }

           marks.add(new Mark(helmetState.getHelmetNo(),
                    helmetState.getDateTime(),
                    helmetState.getLatitude(),
                    helmetState.getLongitude(),
                    setHelmet.getActivation(),
                    helmetState.getLoss(),
                    HelmetStateServiceImpl.helmetWear.get(helmetState.getHelmetNo())));
        }

        return marks;
    }

    @Override
    public List<Helmet> getHelmets(Helmet helmet) {
        System.out.println(helmet.getNo());
        System.out.println(helmet.getActivation());
        List<Helmet> helmets = helmetMapper.selectAll(helmet);

        return helmets != null
                ? helmets
                : new ArrayList<>();
    }

    @Override
    public Helmet getHelmet(Helmet helmet) {
        Helmet viewHelmet = helmetMapper.select(helmet);

        return viewHelmet != null
                ? viewHelmet
                : new Helmet();
    }

    @Override
    public void editHelmet(Helmet helmet) {
        Helmet viewHelmet = helmetMapper.select(helmet);

        if (viewHelmet.getActivation() == 'Y') {
            viewHelmet.setActivation('N');
        } else {
            viewHelmet.setActivation('Y');
        }

        helmetMapper.update(viewHelmet);
    }

    @PostConstruct
    private void initHelmetPair() {
        Kickboard kickboardInfo = new Kickboard();
        for (Helmet helmetInfo : helmetMapper.selectAll(new Helmet())) {
            kickboardInfo.setIp(helmetInfo.getKickboardIp());

            helmetPair.put(helmetInfo.getNo(), kickboardMapper.select(kickboardInfo).getNo());
        }
    }

    @PostConstruct
    private void initHelmetWear() {
        for (HelmetState helmetInfo : helmetStateMapper.selectAll(new HelmetState())) {
            HelmetStateServiceImpl.helmetWear.put(helmetInfo.getHelmetNo(), 'N');
        }
    }
}
