package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AccessController {

    private final AccessService accessService;
    private boolean adminMatch = true;

    @GetMapping("/login")
    public ModelAndView loginForm(){
        ModelAndView modelAndView = new ModelAndView("/access/login");

        modelAndView.addObject("adminMatch", adminMatch);

        adminMatch = true;

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(Admin admin, Errors errors, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/login"));

        if(adminMatch = !errors.hasErrors()) {
            adminMatch = accessService.getAdmin(admin);
            if(adminMatch) {
                httpSession.setAttribute("login", admin);
                modelAndView = new ModelAndView(new RedirectView("/helmets/main"));
            }
        }

        return  modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();

        return new ModelAndView(new RedirectView("/login"));
    }

}
