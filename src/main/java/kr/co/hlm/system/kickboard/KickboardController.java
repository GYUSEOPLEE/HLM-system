package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KickboardController {
    private final KickboardService kickboardService;

    //킥보드 목록 조회 폼
    @GetMapping("/kickboards")
    public ModelAndView getKickboards(){
        return new ModelAndView(new RedirectView("kickboard/list"));
    }

    //킥보드 목록 조회
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Kickboard> getKickboards(Kickboard kickboard){
        List<Kickboard> kickboards = kickboardService.getKickboards(kickboard);

        return kickboards;
    }

    //킥보드 조회
    @GetMapping("/kickboards/{kickboardNo}")
    public ModelAndView getKickboard(Kickboard kickboard){
        Kickboard resultKickboard = kickboardService.getKickboard(kickboard);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("kickboard/view"));
        modelAndView.addObject(resultKickboard);

        return modelAndView;
    }

    //킥보드 수정
    @PutMapping("/kickboards/{kickboardNo}")
    public ModelAndView editKickboard(Kickboard kickboard){
        kickboardService.editKickboard(kickboard);
        Kickboard resultKickboard = kickboardService.getKickboard(kickboard);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("kickboard/{kickboardNo}"));
        modelAndView.addObject(resultKickboard);

        return modelAndView;
    }

    //킥보드 정보 수신
    @PostMapping("/kickboards/info")
    public ReceiveState receiveKickboard(Kickboard kickboard){
        kickboardService.createKickboard(kickboard);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage("OK");
        return receiveState;
    }

}
