package edu.mum.evalplus.web;


import edu.mum.evalplus.model.Survey;
import edu.mum.evalplus.service.IClassOfferedService;
import edu.mum.evalplus.service.IQuestionService;
import edu.mum.evalplus.service.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyController {
    @Autowired
    private ISurveyService surveyService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IClassOfferedService classOfferedService;



    @RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
    public ModelAndView registration(Model model) {
        model.addAttribute("surveyForm", new Survey());
        ModelAndView modelAndView=new ModelAndView("new-survey");
        modelAndView.addObject("questions",questionService.findAll());
        classOfferedService.findAll().forEach(classOffered -> System.out.println(classOffered.getId()));
        modelAndView.addObject("lectures", classOfferedService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/newSurvey", method = RequestMethod.POST)
    public String registration(@ModelAttribute("surveyForm") Survey surveyForm, BindingResult bindingResult, Model model) {
        System.out.println("LOG:" + surveyForm.getClassOffered().getName());
        System.out.println("LOG:" + surveyForm.getEndDate());
        surveyForm.getQuestions().forEach(quest -> System.out.println("LOG:" + quest.getId()));

        // surveyService.save(surveyForm);
        return "redirect:/manageSurvey";
    }

    @RequestMapping(value = "/manageSurvey", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView mav = new ModelAndView("manageSurvey");
        mav.addObject("surveys", surveyService.findAll());
        return mav;
    }

}
