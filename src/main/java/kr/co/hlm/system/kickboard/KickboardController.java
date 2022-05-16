package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KickboardController {
    private final KickboardService kickboardService;

    //킥보드 목록 조회 폼
    @GetMapping("/kickboards")
    public ModelAndView getKickboards(){
        Kickboard kickboard = new Kickboard();
        List<Kickboard> kickboards = kickboardService.getKickboards(kickboard);
        ModelAndView modelAndView = new ModelAndView("kickboard/list");
        modelAndView.addObject("kickboards",kickboards);
        return modelAndView;
    }

    //킥보드 목록 조회
    @PostMapping(value = "/kickboards", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Kickboard> getKickboards(@RequestBody Kickboard kickboard){
        List<Kickboard> kickboards = kickboardService.getKickboards(kickboard);

        return kickboards;
    }

    //킥보드 조회 (문서 바꾸기)
    @GetMapping("/kickboards/{no}")
    public ModelAndView getKickboard(@PathVariable String no){
        Kickboard resultKickboard = kickboardService.getKickboard(no);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("kickboard/view"));
        System.out.println(resultKickboard);
        modelAndView.addObject(resultKickboard);

        return modelAndView;
    }

    //킥보드 수정
    @PutMapping("/kickboards/{no}")
    public ModelAndView editKickboard(@RequestBody Kickboard kickboard){
        kickboardService.editKickboard(kickboard);
        Kickboard resultKickboard = kickboardService.getKickboard(kickboard.getNo());
        ModelAndView modelAndView = new ModelAndView(new RedirectView("kickboard/{no}"));
        modelAndView.addObject(resultKickboard);

        return modelAndView;
    }

    //킥보드 정보 수신
    @PostMapping("/kickboards/info")
    public ReceiveState receiveKickboard(@RequestBody Kickboard kickboard){
        kickboardService.createKickboard(kickboard);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");
        return receiveState;
    }

}
