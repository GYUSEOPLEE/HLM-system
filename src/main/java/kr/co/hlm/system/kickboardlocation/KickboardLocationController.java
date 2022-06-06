package kr.co.hlm.system.kickboardlocation;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kickboards")
public class KickboardLocationController {
    private final KickboardLocationService kickboardLocationService;

    //킥보드 위치 정보 수신
    @PostMapping("/location")
    public ReceiveState receiveKickboardLocation(@RequestBody KickboardLocation kickboardLocation) {
        //kickboardLocationService.createKickboardLocation(kickboardLocation);

        System.out.println(kickboardLocation.toString());

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");

        return receiveState;
    }
}
