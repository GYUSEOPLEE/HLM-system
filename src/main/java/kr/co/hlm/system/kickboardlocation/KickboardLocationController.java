package kr.co.hlm.system.kickboardlocation;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KickboardLocationController {
    private final KickboardLocationService kickboardLocationService;

    //킥보드 위치 정보 수신
    @PostMapping("/kickboards/location")
    public ReceiveState receiveKickboardLocation(KickboardLocation kickboardLocation) {
        kickboardLocationService.createKickboardLocation(kickboardLocation);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");
        return null;
    }
}
