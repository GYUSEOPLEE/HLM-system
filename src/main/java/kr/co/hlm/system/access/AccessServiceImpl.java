package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements HandlerInterceptor, AccessService{
    private final AdminMapper adminMapper;

    @Override
    public boolean getAdmin(Admin admin) {

        int gab = adminMapper.select(admin);
        boolean result = false;

        if(gab == 1) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("id") != null && session.getAttribute("id") != ""){
            System.out.println("intercepter");
            session.setMaxInactiveInterval(30 * 60);
           return true;
        }

        response.sendRedirect("helmets/list");
        return false;
    }
}
