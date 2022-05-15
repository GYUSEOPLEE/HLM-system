package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AccessController {

    private final AccessService accessService;

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("/access/login");
    }

    @PostMapping("/login")
    public ModelAndView login(Admin admin, HttpSession httpSession){

        System.out.println(admin.getId());
        System.out.println(admin.getPassword());

        if("".equals(admin.getId()) || "".equals(admin.getPassword())){
            return new ModelAndView(new RedirectView("/login"));//입력값 없으면 로그인창으로
        }else {
            httpSession.setAttribute("id",admin.getId());//해당하는 아이디가 있으면 세션 생성
            if(accessService.getAdmin(admin)){
                httpSession.setAttribute("id",admin.getId());
            } else {
                return new ModelAndView(new RedirectView("/login"));
            }
            if(httpSession.getAttribute("id")==null){
                System.out.println("not login");

                return new ModelAndView(new RedirectView("/login"));// 관리자 정보가 다르면 로그인창으로
            }
        }
        return new ModelAndView(new RedirectView("/helmets/list"));
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        return new ModelAndView(new RedirectView("/login"));
    }

}
