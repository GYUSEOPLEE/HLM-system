package kr.co.hlm.system.access;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccessController {
    private final AccessService accessService;

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
