package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.management.ReceiveState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/helmetstates")
public class HelmetStateController {
    @GetMapping
    public ModelAndView getHelmetStates() {
        //WEB-INF/jsp/state/list.jsp
        return null;
    }

    @PostMapping
    public String getHelmetStates(HelmetState helmetState) {
        return null;
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmetState(HelmetState helmetState) {
        //WEB-INF/jsp/state/view.jsp
        return null;
    }

    @PostMapping("/location")
    public ReceiveState receiveHelmetLocation(HelmetState helmetState) {
        return null;
    }

    @PostMapping("/wear")
    public ReceiveState receiceHelmetWear(HelmetState helmetState) {
        return null;
    }
}
