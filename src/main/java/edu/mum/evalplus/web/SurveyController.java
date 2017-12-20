package edu.mum.evalplus.web;


import edu.mum.evalplus.model.Question;
import edu.mum.evalplus.model.Survey;
import edu.mum.evalplus.service.*;
import edu.mum.evalplus.util.SurveyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class SurveyController {
    @Autowired
    private ISurveyService surveyService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IClassOfferedService classOfferedService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IEmailService emailService;


    @RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
    public ModelAndView surveyForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("surveyForm");
        modelAndView.addObject("questionList", questionService.findAll());
        modelAndView.addObject("classList", classOfferedService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/newSurvey", method = RequestMethod.POST)
    public String createSurvey(HttpServletRequest request, Principal principal) {
        surveyService.save(new Survey().bindParam(request));
        return "redirect:/manageSurvey";
    }

    @RequestMapping(value = "/manageSurvey", method = RequestMethod.GET)
    public ModelAndView manage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("manageSurvey");
        mav.addObject("surveys", surveyService.findAll());
        return mav;
    }


    @RequestMapping(value = "/takeSurvey", method = RequestMethod.GET)
    public String takeSurvey(Model model, Principal principal) {
        model.addAttribute("surveys", surveyService.findStudentSurvey(principal.getName()));
        return "studentSurvey";
    }

    @RequestMapping(value = "/takeSurvey/{id}", method = RequestMethod.GET)
    public String takeSurvey(@PathVariable("id") int id, Model model, Principal principal) {
        model.addAttribute("survey", surveyService.find(id));
        return "takeSurvey";
    }

    @RequestMapping(value = "/takeSurvey", method = RequestMethod.POST)
    public String saveSurvey(@RequestParam("surveyId") Integer surveyId, HttpServletRequest request, Model model, Principal principal) {
        Survey survey = surveyService.find(surveyId).prepareAnswers(request, studentService.findByUsername(principal.getName()));
        surveyService.save(survey);
        return "home";
    }


    @RequestMapping(value = "/surveyDetails/{id}", method = RequestMethod.GET)
    public String surveyDetails(@PathVariable("id") int id, Model model, Principal principal) {
        Survey survey = surveyService.find(id);
        Map<Question, SurveyReport> reports = survey.prepareReport();
        model.addAttribute("reports", reports);
        return "surveyDetails";
    }


}
