package kr.co.hlm.system.helmet;

import kr.co.hlm.system.kickboard.Kickboard;
import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helmets")
public class HelmetController {
    private final HelmetService helmetService;

    @GetMapping
    public ModelAndView getHelmets() {
        ModelAndView modelAndView = new ModelAndView("helmet/list");

        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Helmet> getHelmets(@RequestBody Helmet helmet) {
        List<Helmet> helmets = helmetService.getHelmets(helmet);

        return helmets;
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmet(Helmet helmet) {
        ModelAndView modelAndView = new ModelAndView("helmet/view");
        modelAndView.addObject(helmetService.getHelmet(helmet));

        return modelAndView;
    }

    @PostMapping("/{no}")
    public ModelAndView editHelmet(Helmet helmet) {
        helmetService.editHelmet(helmet);

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/helmets/" + helmet.getNo()));

        return modelAndView;
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmet(@RequestBody Helmet helmet) {
        helmetService.createHelmet(helmet);

        ReceiveState state = new ReceiveState();
        state.setCode("200");
        state.setMessage(null);

        return state;
    }

}
