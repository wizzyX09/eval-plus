package edu.mum.evalplus.web;


import edu.mum.evalplus.model.User;
import edu.mum.evalplus.service.SecurityService;
import edu.mum.evalplus.service.UserService;
import edu.mum.evalplus.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/generateAccount", method = RequestMethod.GET)
    public String addAccount(Model model) {
        model.addAttribute("userForm", new User());

        return "generateAccount";
    }

    @RequestMapping(value = "/generateAccount", method = RequestMethod.POST)
    public String addAccount(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userService.save(userForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/manageAccount", method = RequestMethod.GET)
    public String manageAccount(Model model) {
        model.addAttribute("userForm", new User());

        return "manageAccount";
    }

    @RequestMapping(value = "/manageAccount", method = RequestMethod.POST)
    public String manageAccount(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {



        return "redirect:/welcome";
    }


}
