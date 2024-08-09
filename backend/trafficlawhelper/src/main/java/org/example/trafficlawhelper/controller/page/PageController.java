package org.example.trafficlawhelper.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/insurance")
    public String insurance() {
        return "insurance_test";
    }

}
