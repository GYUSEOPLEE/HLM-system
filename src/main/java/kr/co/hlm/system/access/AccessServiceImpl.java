package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService{
    private final AdminMapper adminMapper;

    @Override
    public boolean getAdmin(Admin admin) {

        int gab = adminMapper.select(admin);
        boolean result = false;

        if(gab != 0) {
            result = true;
        }

        return result;
    }
}
