package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.HelmetStatePageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/helmetstates")
public class HelmetStateController {
    private final HelmetStateService helmetStateService;
    private final HelmetStatePageUtil helmetStatePageUtil;

    @GetMapping("/{helmetNo}")
    public ModelAndView getHelmetStates(@PathVariable String helmetNo) {
        ModelAndView modelAndView = new ModelAndView("state/list");
        modelAndView.addObject("helmetNo", helmetNo);

        return modelAndView;
    }

    @PostMapping(value = "/{helmetNo}/{pageNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getHelmetStates(@PathVariable int pageNo, @RequestBody HelmetState helmetState) {
        Page page = helmetStatePageUtil.setPage(helmetState.getHelmetNo(), helmetStateService.getHelmetStates(helmetState).size(), pageNo);

        helmetState.setNo((pageNo - 1) * 5);

        List<HelmetState> helmetStates = helmetStateService.getHelmetStates(helmetState);

        return  helmetStatePageUtil.drawHelmetStaetePage(page, helmetStates);
    }

    @PostMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmetLocation(@RequestBody HelmetState helmetState) {
        helmetState.setLatitude(helmetState.getLatitude() + 0.319078);
        helmetState.setLongitude(helmetState.getLongitude() + 0.030081);

        log.info("H LOC");
        log.info("latitude  : " + helmetState.getLatitude());
        log.info("longitude : " + helmetState.getLongitude());
        log.info("helmetNo  : " + helmetState.getHelmetNo() + "\n");

        helmetStateService.createHelmetState(helmetState);

        ReceiveState state = new ReceiveState();
        state.setCode("200");
        state.setMessage(null);

        return state;
    }

    //완성
    @PostMapping(value = "/wear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiceHelmetWear(@RequestBody HelmetState helmetState) {
        log.info("Wear");
        log.info("wear     : " + helmetState.getWear() + "\n");

        helmetStateService.editHelmetState(helmetState);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage(null);

        return receiveState;
    }
}
