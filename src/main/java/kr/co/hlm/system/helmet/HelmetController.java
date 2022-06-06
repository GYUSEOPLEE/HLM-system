package kr.co.hlm.system.helmet;


import kr.co.hlm.system.helmetstate.HelmetState;
import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.HelmetPageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helmets")
public class HelmetController {
    private final HelmetService helmetService;
    private final HelmetPageUtil helmetPageUtil;

    @GetMapping("/main")
    public ModelAndView getMainForm() {
        return new ModelAndView("helmet/main");
    }

    @PostMapping(value = "/main", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Mark> getMainPage(@RequestBody Helmet helmet) {
        return helmetService.getMarks(helmet);
    }

    @GetMapping
    public ModelAndView getHelmetsForm() {
        return new ModelAndView("helmet/list");
    }

    @PostMapping(value = "/{pageNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getHelmets(@PathVariable int pageNo, @RequestBody Helmet helmet) {
        String drawPage;

        Page page = helmetPageUtil.setPage(helmet.getNo(), helmetService.getHelmets(helmet).size(), pageNo);

        helmet.setModel("" + (pageNo - 1) * 5);

        List<Helmet> helmets = helmetService.getHelmets(helmet);

        drawPage = helmetPageUtil.drawHelmetPage(page, helmets);

        return drawPage;
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmet(Helmet helmet) {
        ModelAndView modelAndView = new ModelAndView("helmet/view");
        modelAndView.addObject(helmetService.getHelmet(helmet));

        return modelAndView;
    }

    //문서추가
    @PostMapping("/{no}/edit")
    public ModelAndView editHelmet(Helmet helmet) {
        helmetService.editHelmet(helmet);

        return new ModelAndView(new RedirectView("/helmets/" + helmet.getNo()));
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmet(@RequestBody @Valid Helmet helmet) {
        helmetService.createHelmet(helmet);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage(null);

        return receiveState;
    }

}
