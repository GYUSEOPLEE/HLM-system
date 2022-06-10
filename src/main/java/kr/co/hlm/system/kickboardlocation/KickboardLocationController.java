package kr.co.hlm.system.kickboardlocation;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/kickboards")
public class KickboardLocationController {
    private final KickboardLocationService kickboardLocationService;

    //킥보드 위치 정보 수신
    @PostMapping("/location")
    public ReceiveState receiveKickboardLocation(@RequestBody KickboardLocation kickboardLocation) {
        log.info("====================INFO====================");
        log.info("| receiveKickboardLocation");
        log.info("| dateTime    : " + kickboardLocation.getDateTime());
        log.info("| latitude    : " + kickboardLocation.getLatitude());
        log.info("| longitude   : " + kickboardLocation.getLongitude());
        log.info("| kickboardNo : " + kickboardLocation.getKickboardNo());
        log.info("=============================================");

        kickboardLocationService.createKickboardLocation(kickboardLocation);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");

        return receiveState;
    }
}
