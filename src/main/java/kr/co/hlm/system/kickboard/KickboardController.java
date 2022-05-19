package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.KickboardPageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kickboards")
public class KickboardController {
    private final KickboardService kickboardService;
    private final KickboardPageUtil kickboardPageUtil;

    //킥보드 목록 조회 폼
    @GetMapping
    public ModelAndView getKickboards(){
        ModelAndView modelAndView = new ModelAndView("kickboard/list");
        return modelAndView;
    }

    //킥보드 목록 조회 (문서 바꾸기)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String getKickboards(@RequestBody Page page){
        String drawPage = "";
        Page newPage = kickboardPageUtil.setPage(kickboardService.getKickboardsView(page).size(),page.getPageNo());
        List<Kickboard> kickboardList = kickboardService.getKickboardsView(newPage);
        drawPage = kickboardPageUtil.drawPage(newPage, kickboardList);

        return drawPage;
    }

    //킥보드 조회 (문서 바꾸기)
    @GetMapping("/{no}")
    public ModelAndView getKickboard(@PathVariable String no){
        ModelAndView modelAndView = new ModelAndView("kickboard/view");
        Kickboard resultKickboard = kickboardService.getKickboard(no);
        modelAndView.addObject("kickboard",resultKickboard);

        return modelAndView;
    }

    //킥보드 수정
    @PostMapping("/{no}")
    public ModelAndView editKickboard(Kickboard kickboard){
        kickboardService.editKickboard(kickboard);
        Kickboard resultKickboard = kickboardService.getKickboard(kickboard.getNo());
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/kickboards/" + kickboard.getNo()));
        modelAndView.addObject("kickboard",resultKickboard);

        return modelAndView;
    }

    //킥보드 정보 수신
    @PostMapping("/info")
    public ReceiveState receiveKickboard(@RequestBody Kickboard kickboard){
        kickboardService.createKickboard(kickboard);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");
        return receiveState;
    }

}
