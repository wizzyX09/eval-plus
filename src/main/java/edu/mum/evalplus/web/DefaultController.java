package edu.mum.evalplus.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/logout")
    public String welcome(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
