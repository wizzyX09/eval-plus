package edu.mum.evalplus.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {


    @RequestMapping("*")
    public String welcome(Model model) {
        return "404";
    }

    @RequestMapping("/403")
    public String welcome() {
        return "403";
    }
}
