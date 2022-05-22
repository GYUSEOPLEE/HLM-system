package kr.co.hlm.system.management;

import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.kickboardlocation.KickboardLocation;
import org.springframework.stereotype.Component;

@Component
public class HelmetLostUtil {

    //헬멧 킥보드 거리에 따른 분실여부 계산
    public boolean helmetLostCalculation(HelmetState helmetState, KickboardLocation kickboardLocation){
        double theta = helmetState.getLongitude() - kickboardLocation.getLongitude();
        double dist = Math.sin(deg2rad(helmetState.getLatitude())) * Math.sin(deg2rad(kickboardLocation.getLatitude()))
                + Math.cos(deg2rad(helmetState.getLongitude())) * Math.cos(deg2rad(kickboardLocation.getLongitude()))
                * Math.cos(deg2rad(theta));

        boolean result = false;

        if(dist > 300){
            result = true;
        }

        return result;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
