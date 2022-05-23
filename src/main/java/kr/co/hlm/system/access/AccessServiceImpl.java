package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
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
        Admin selectAdmin = adminMapper.select(admin);

        return selectAdmin != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            Admin isLogin = (Admin) httpSession.getAttribute("login");
            if(isLogin != null) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}
