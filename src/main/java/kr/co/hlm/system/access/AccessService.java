package kr.co.hlm.system.access;

import org.springframework.stereotype.Service;

@Service
public interface AccessService {
    public boolean getAdmin(Admin admin);
}
