package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.KickboardPageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/kickboards")
public class KickboardController {
    private final KickboardService kickboardService;
    private final KickboardPageUtil kickboardPageUtil;

    @GetMapping
    public ModelAndView getKickboardsForm(){
        return new ModelAndView("kickboard/list");
    }

    @PostMapping(value = "/{pageNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getKickboards(@PathVariable int pageNo, @RequestBody Kickboard kickboard){
        Page page = kickboardPageUtil.setPage(kickboard.getNo(), kickboardService.getKickboards(kickboard).size(), pageNo);

        kickboard.setModel("" + (pageNo - 1) * 5);

        List<Kickboard> kickboards = kickboardService.getKickboards(kickboard);

        return kickboardPageUtil.drawKickboardPage(page, kickboards);
    }

    @GetMapping("/{no}")
    public ModelAndView getKickboard(Kickboard kickboard){
        ModelAndView modelAndView = new ModelAndView("kickboard/view");
        modelAndView.addObject(kickboardService.getKickboard(kickboard));

        return modelAndView;
    }

    @PostMapping("/{no}/edit")
    public ModelAndView editKickboard(Kickboard kickboard){
        kickboardService.editKickboard(kickboard);
        Kickboard resultKickboard = kickboardService.getKickboard(kickboard);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/kickboards/" + kickboard.getNo()));
        modelAndView.addObject("kickboard",resultKickboard);

        return modelAndView;
    }

    @PostMapping("/info")
    public ReceiveState receiveKickboard(@RequestBody @Valid Kickboard kickboard) {
        log.info("K INFO");
        log.info("no    : " + kickboard.getNo());
        log.info("model : " + kickboard.getModel());
        log.info("ip    : " + kickboard.getIp() + "\n");

        kickboardService.createKickboard(kickboard);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");

        return receiveState;
    }
}
