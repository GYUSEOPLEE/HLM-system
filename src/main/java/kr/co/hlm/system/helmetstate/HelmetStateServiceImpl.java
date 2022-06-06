package kr.co.hlm.system.helmetstate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.hlm.system.helmet.Helmet;
import kr.co.hlm.system.helmet.HelmetMapper;
import kr.co.hlm.system.helmet.HelmetService;
import kr.co.hlm.system.helmet.HelmetServiceImpl;
import kr.co.hlm.system.kickboardlocation.KickboardLocation;
import kr.co.hlm.system.kickboardlocation.KickboardLocationMapper;
import kr.co.hlm.system.kickboardlocation.KickboardLocationService;
import kr.co.hlm.system.management.HelmetLostUtil;
import kr.co.hlm.system.management.ManagementService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HelmetStateServiceImpl implements HelmetStateService{
    private final HelmetStateMapper helmetStateMapper;
    private final HelmetService helmetService;
    private final KickboardLocationService kickboardLocationService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final HelmetLostUtil helmetLostUtil;
    private final ManagementService managementService;
    public static Map<String, Character> helmetWear = new HashMap<>();

    @Override
    public void createHelmetState(HelmetState helmetState) {
        Helmet helmet = new Helmet();
        helmet.setNo(helmetState.getHelmetNo());
        helmet = helmetService.getHelmet(helmet);

        KickboardLocation kickboardLocation = new KickboardLocation();
        kickboardLocation.setKickboardNo(HelmetServiceImpl.helmetPair.get(helmet.getNo()));

        //T면 분실X F면 분실
        if (helmetLostUtil.helmetLostCalculation(helmetState, kickboardLocationService.getKickboardLocation(kickboardLocation))) {
            helmetState.setLoss('N');
        } else {
            helmetState.setLoss('Y');

            managementService.sendHelmetLoss(helmetService.getHelmet(helmet));
        }

        helmetState.setWear(helmetWear.get(helmetState.getHelmetNo()));

        helmetStateMapper.insert(helmetState);
        applicationEventPublisher.publishEvent(helmetState);
    }

    @Override
    public List<HelmetState> getHelmetStates(HelmetState helmetState) {
       List<HelmetState> helmetStates = helmetStateMapper.selectAll(helmetState);

       for (HelmetState insertHelmetWear : helmetStates) {
           insertHelmetWear.setWear(helmetWear.get(insertHelmetWear.getHelmetNo()));
       }

       return helmetStates;
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
}
