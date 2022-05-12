package kr.co.hlm.system.kickboardlocation;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class KickboardLocationController {
    private final KickboardLocationService kickboardLocationService;

    @PostMapping("/kickboards/location")
    public ReceiveState receiveKickboardLocation(KickboardLocation kickboardLocation) {
        return null;
    }
}
