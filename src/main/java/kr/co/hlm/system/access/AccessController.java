package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AccessController {
    private final AccessService accessService;

    @GetMapping("/loginview")
    public ModelAndView loginForm(){
        return new ModelAndView("access/login.jsp");
    }

    @PostMapping("/login")
    public ModelAndView login(Admin admin, HttpSession httpSession){

        if(accessService.getAdmin(admin)){
            return new ModelAndView(new RedirectView("access/login"));//입력값 없으면 로그인창으로
        }else {
            if(accessService.getAdmin(admin)){
                httpSession.setAttribute("id",admin.getId());//해당하는 아이디가 있으면 세션 생성
            }
            if(httpSession.getAttribute("sessionId")==null) {
                return new ModelAndView(new RedirectView("access/login"));// 관리자 정보가 다르면 로그인창으로
            }
        }

        return new ModelAndView(new RedirectView("helmets/list"));
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        return new ModelAndView(new RedirectView("access/login"));
    }

}
