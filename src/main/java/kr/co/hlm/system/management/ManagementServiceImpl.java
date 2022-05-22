package kr.co.hlm.system.management;

import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.helmetstate.HelmetStateMapper;
import kr.co.hlm.system.kickboardlocation.KickboardLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService{
    private final HelmetLostUtil helmetLostUtil;
    private final HelmetStateMapper helmetStateMapper;
    @Override
    public void sendHelmetLoss(HelmetState helmetState, KickboardLocation kickboardLocation) {
        boolean lost = helmetLostUtil.helmetLostCalculation(helmetState, kickboardLocation);

        if(lost) {
            helmetState.setLoss('Y');
            helmetStateMapper.update(helmetState);
        }
    }

}
