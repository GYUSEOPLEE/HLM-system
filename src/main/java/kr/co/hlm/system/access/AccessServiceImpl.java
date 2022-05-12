package kr.co.hlm.system.access;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MapperScan(basePackages = "")
public class AccessServiceImpl implements AccessService{
    @Autowired
    AdminMapper adminMapper;

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
