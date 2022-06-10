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
        String drawPage = "";

        Page page = helmetStatePageUtil.setPage(helmetState.getHelmetNo(), helmetStateService.getHelmetStates(helmetState).size(), pageNo);

        helmetState.setNo((pageNo - 1) * 5);

        List<HelmetState> helmetStates = helmetStateService.getHelmetStates(helmetState);

        drawPage = helmetStatePageUtil.drawHelmetStaetePage(page, helmetStates);

        return drawPage;
    }

    @PostMapping(value = "/location", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmetLocation(@RequestBody HelmetState helmetState) {
        log.info("====================INFO====================");
        log.info("| receiveHelmetLocation");
        log.info("| dateTime  : " + helmetState.getDateTime());
        log.info("| latitude  : " + helmetState.getLatitude());
        log.info("| longitude : " + helmetState.getLongitude());
        log.info("| helmetNo  : " + helmetState.getHelmetNo());
        log.info("=============================================");

        helmetStateService.createHelmetState(helmetState);

        ReceiveState state = new ReceiveState();
        state.setCode("200");
        state.setMessage(null);

        return state;
    }

    //완성
    @PostMapping(value = "/wear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiceHelmetWear(@RequestBody HelmetState helmetState) {
        log.info("====================INFO====================");
        log.info("| receiveHelmetWear");
        log.info("| helmetNo : " + helmetState.getHelmetNo());
        log.info("| wear     : " + helmetState.getWear());
        log.info("=============================================");

        helmetStateService.editHelmetState(helmetState);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage(null);

        return receiveState;
    }
}
