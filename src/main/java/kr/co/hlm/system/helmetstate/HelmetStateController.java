package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helmetstates")
public class HelmetStateController {
    private final HelmetStateService helmetStateService;

    @GetMapping
    public ModelAndView getHelmetStates() {
        ModelAndView modelAndView = new ModelAndView("state/list");

        return modelAndView;
    }

    @PostMapping
    public List<HelmetState> getHelmetStates(HelmetState helmetState) {
        List<HelmetState> helmetStates = helmetStateService.getHelmetStates(helmetState);

        return helmetStates;
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmetState(HelmetState helmetState) {
        //WEB-INF/jsp/state/view.jsp
        return null;
    }

    @PostMapping("/location")
    public ReceiveState receiveHelmetLocation(HelmetState helmetState) {
        helmetStateService.createHelmetState(helmetState);

        ReceiveState state = new ReceiveState();
        state.setCode("200");
        state.setMessage(null);

        return state;
    }

    @PostMapping("/wear")
    public ReceiveState receiceHelmetWear(HelmetState helmetState) {
        HelmetStateServiceImpl.helmetWear.put(helmetState.getHelmetNo(), helmetState.getWear());

        ReceiveState state = new ReceiveState();
        state.setCode("200");
        state.setMessage(null);

        return state;
    }
}
