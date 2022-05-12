package kr.co.hlm.system.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {

    @Autowired
    public AccessService accessService;

    @RequestMapping("/access")
    public String access(){
        Admin admin = new Admin();
        admin.setId("id");
        admin.setPassword("pw");

        Boolean result = accessService.getAdmin(admin);
        String strResult = result.toString();
        return strResult;
    }
}
