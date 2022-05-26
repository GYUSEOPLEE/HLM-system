package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.helmet.Helmet;
import kr.co.hlm.system.helmet.HelmetMapper;
import kr.co.hlm.system.helmet.HelmetServiceImpl;
import kr.co.hlm.system.kickboard.Kickboard;
import kr.co.hlm.system.kickboard.KickboardMapper;
import kr.co.hlm.system.kickboardlocation.KickboardLocation;
import kr.co.hlm.system.kickboardlocation.KickboardLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HelmetStateServiceImpl implements HelmetStateService{
    private final HelmetMapper helmetMapper;
    private final HelmetStateMapper helmetStateMapper;
    private final KickboardMapper kickboardMapper;
    private final KickboardLocationMapper kickboardLocationMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    public static Map<String, Character> helmetWear = new HashMap<String, Character>();

    @Override
    public void createHelmetState(HelmetState helmetState) {
        Helmet helmet = new Helmet();
        KickboardLocation kickboardLocation = new KickboardLocation();

        helmet.setNo(helmetState.getHelmetNo());
        kickboardLocation.setKickboardNo(HelmetServiceImpl.helmetPair.get(helmetMapper.select(helmet).getNo()));

        //T면 분실X F면 분실
        if (helmetLostCalculation(helmetState, kickboardLocationMapper.select(kickboardLocation))) {
            helmetState.setLoss('N');
        } else {
            helmetState.setLoss('Y');
        }

        helmetState.setWear(helmetWear.get(helmetState.getHelmetNo()));

        helmetStateMapper.insert(helmetState);
        applicationEventPublisher.publishEvent(helmetState);
    }

    @Override
    public List<HelmetState> getHelmetStates(HelmetState helmetState) {
       List<HelmetState>  helmetStates = helmetStateMapper.selectAll(helmetState);

       return helmetStates != null
               ? helmetStates
               : new ArrayList<HelmetState>();
    }

    @Override
    public HelmetState getHelmetState(HelmetState helmetState) {
        HelmetState viewHelmetState = helmetStateMapper.select(helmetState);

        return viewHelmetState != null
                ? viewHelmetState
                : new HelmetState();
    }

    @Override
    public void editHelmetState(HelmetState helmetState) {
        helmetWear.put(helmetState.getHelmetNo(), helmetState.getWear());
    }

    private boolean helmetLostCalculation(HelmetState helmetState, KickboardLocation kickboardLocation){
        boolean result = true;
        double theta = helmetState.getLongitude() - kickboardLocation.getLongitude();
        double dist = Math.sin(deg2rad(helmetState.getLatitude())) * Math.sin(deg2rad(kickboardLocation.getLatitude()))
                + Math.cos(deg2rad(helmetState.getLatitude())) * Math.cos(deg2rad(kickboardLocation.getLatitude()))
                * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 60 * 1.1515;
        dist *= 1609.344;

        if(dist > 30){
            result = false;
        }

        System.out.println(dist);

        return result;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
