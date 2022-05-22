package kr.co.hlm.system.helmetstate;

import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.HelmetStatePageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helmetstates")
public class HelmetStateController {
    private final HelmetStateService helmetStateService;
    private final HelmetStatePageUtil helmetStatePageUtil;

    //문서 추가
    @GetMapping("/{helmetNo}")
    public ModelAndView getHelmetStates(@PathVariable String helmetNo) {
        ModelAndView modelAndView = new ModelAndView("state/list");
        modelAndView.addObject("helmetNo", helmetNo);

        return modelAndView;
    }

    //문서 추가
    @PostMapping(value = "/{helmetNo}/{pageNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getHelmetStates(@PathVariable int pageNo, @RequestBody HelmetState helmetState) {
        System.out.println(helmetState.toString());

        String drawPage = "";

        Page page = helmetStatePageUtil.setPage(helmetState.getHelmetNo(), helmetStateService.getHelmetStates(helmetState).size(), pageNo);

        helmetState.setNo((pageNo - 1) * 5);

        List<HelmetState> helmetStates = helmetStateService.getHelmetStates(helmetState);

        drawPage = helmetStatePageUtil.drawPage(page, helmetStates);

        return drawPage;
    }
//    문서 추가
//    @GetMapping("/{no}")
//    public ModelAndView getHelmetState(HelmetState helmetState) {
//        //WEB-INF/jsp/state/view.jsp
//        return null;
//    }

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
