package kr.co.hlm.system.helmet;

import kr.co.hlm.system.management.ReceiveState;
import kr.co.hlm.system.page.HelmetPageUtil;
import kr.co.hlm.system.page.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Log4j2
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

        Page page = helmetPageUtil.setPage(helmet.getNo(), helmetService.getHelmets(helmet).size(), pageNo);

        helmet.setModel("" + (pageNo - 1) * 5);

        List<Helmet> helmets = helmetService.getHelmets(helmet);

        return helmetPageUtil.drawHelmetPage(page, helmets);
    }

    @GetMapping("/{no}")
    public ModelAndView getHelmet(Helmet helmet) {
        ModelAndView modelAndView = new ModelAndView("helmet/view");
        modelAndView.addObject(helmetService.getHelmet(helmet));

        return modelAndView;
    }

    @PostMapping("/{no}/edit")
    public ModelAndView editHelmet(Helmet helmet) {
        helmetService.editHelmet(helmet);

        return new ModelAndView(new RedirectView("/helmets/" + helmet.getNo()));
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReceiveState receiveHelmet(@RequestBody @Valid Helmet helmet) {
        log.info("H INFO");
        log.info("no          : " + helmet.getNo());
        log.info("model       : " + helmet.getModel());
        log.info("ip          : " + helmet.getIp());
        log.info("kickboardIp : " + helmet.getKickboardIp());
        log.info("size        : " + helmet.getSize() + "\n");

        helmetService.createHelmet(helmet);

        ReceiveState receiveState = new ReceiveState();
        receiveState.setCode("200");
        receiveState.setMessage(null);

        return receiveState;
    }

}
