package kr.co.hlm.system.helmet;

import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.helmetstate.HelmetStateMapper;
import kr.co.hlm.system.helmetstate.HelmetStateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HelmetServiceImpl implements HelmetService{
    private final HelmetMapper helmetMapper;
    private final HelmetStateMapper helmetStateMapper;

    @Override
    public void createHelmet(Helmet helmet) {
        helmetMapper.insert(helmet);
    }

    @Override
    public List<Mark> getMarks(Helmet helmet) {
        System.out.println(HelmetStateServiceImpl.helmetWear.size());

        if (HelmetStateServiceImpl.helmetWear.size() == 0) {
            List<HelmetState> helmetStates = helmetStateMapper.selectAll(new HelmetState());

            for (HelmetState helmetInfo : helmetStates) {
                HelmetStateServiceImpl.helmetWear.put(helmetInfo.getHelmetNo(), 'N');
            }
        }

        System.out.println(HelmetStateServiceImpl.helmetWear.size());

        List<Mark> marks = new ArrayList<Mark>();
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
        List<Helmet> helmets = helmetMapper.selectAll(helmet);

        return helmets != null
                ? helmets
                : new ArrayList<Helmet>();
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
}
