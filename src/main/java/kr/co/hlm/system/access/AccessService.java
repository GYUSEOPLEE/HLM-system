package kr.co.hlm.system.access;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public interface AccessService {
    public boolean getAdmin(Admin admin);
    public void login(Admin admin, HttpSession httpSession);
}
