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

    @PostMapping("/location")
    public ReceiveState receiveKickboardLocation(@RequestBody KickboardLocation kickboardLocation) {
        kickboardLocation.setLatitude(kickboardLocation.getLatitude() + 0.319078);
        kickboardLocation.setLongitude(kickboardLocation.getLongitude() + 0.030081);

        log.info("K LOC");
        log.info("latitude    : " + kickboardLocation.getLatitude());
        log.info("longitude   : " + kickboardLocation.getLongitude() + "\n");

        kickboardLocationService.createKickboardLocation(kickboardLocation);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");

        return receiveState;
    }
}
