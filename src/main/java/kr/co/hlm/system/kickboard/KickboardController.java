package kr.co.hlm.system.kickboard;

import kr.co.hlm.system.management.ReceiveState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KickboardController {
    private final KickboardService kickboardService;

    @GetMapping("/kickboards")
    public ModelAndView getKickboards(){
        return null;
    }

    @PostMapping("/kickboards")
    public ModelAndView getKickboards(Kickboard kickboard){
        return null;
    }

    @GetMapping("/kickboards/{kickboardNo}")
    public Map<String, Object> getKickboard(Kickboard kickboard){
        return null;
    }

    @PutMapping("/kickboards/{kickboardNo}")
    public ModelAndView editKickboard(Kickboard kickboard){
        return null;
    }

    @PostMapping("/kickboards/recive")
    public ReceiveState receiveKickboard(Kickboard kickboard){
        return null;
    }

}
