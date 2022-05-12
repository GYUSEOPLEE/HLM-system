package kr.co.hlm.system.helmet;

import kr.co.hlm.system.management.ReceiveState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/helmets")
public class HelmetController {
    private HelmetService helmetService;

    @GetMapping
    public ModelAndView getHelmets() {
        ModelAndView modelAndView = new ModelAndView("helmet/list");
        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Helmet> getHelmets(Helmet helmet) {
        List<Helmet> helmets = helmetService.getHelmets(helmet);

        return helmets;
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
