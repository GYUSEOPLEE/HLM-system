package kr.co.hlm.system.management;

import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.kickboardlocation.KickboardLocation;
import org.springframework.stereotype.Component;

@Component
public class HelmetLostUtil {
    public boolean helmetLostCalculation(HelmetState helmetState, KickboardLocation kickboardLocation){
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
