package kr.co.hlm.system.helmetstate.helmet;

import kr.co.hlm.system.management.ReceiveState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/helmets")
public class HelmetController {
    @PostMapping
    public ModelAndView getHelmets() {
        //WEB-INF/jsp/helmet/list.jsp
        return null;
    }

    @GetMapping
    public String getHelmets(Helmet helmet) {
        return null;
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmet(Helmet helmet) {
        //WEB-INF/jsp/helmet/view.jsp
        return null;
    }

    @PostMapping("/{no}")
    public ModelAndView editHelmet(Helmet helmet) {
        ///helmets/{헬멧 일련번호}
        return null;
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmet(Helmet helmet) {
        return null;
    }

}
