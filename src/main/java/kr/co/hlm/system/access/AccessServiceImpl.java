package kr.co.hlm.system.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AccessServiceImpl implements HandlerInterceptor, AccessService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean getAdmin(Admin admin) {

        int gab = adminMapper.select(admin);
        boolean result = false;

        if(gab == 1) {
            result = true;
        }

        return result;
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession httpSession = request.getSession();
//        if(httpSession.getAttribute("id") != null){
//            System.out.println("실행");
//            httpSession.setMaxInactiveInterval(30*60);
//            response.sendRedirect("helmets/list");
//            return true;
//        } else {
//            System.out.println("login실행");
//            response.sendRedirect("/login");
//            return false;
//        }
//    }
}
