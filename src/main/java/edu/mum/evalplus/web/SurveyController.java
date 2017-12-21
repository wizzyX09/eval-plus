package edu.mum.evalplus.web;


import edu.mum.evalplus.model.*;
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
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
    public ModelAndView surveyForm(Principal principal, Model model) {
        ModelAndView modelAndView = new ModelAndView("surveyForm");
        modelAndView.addObject("questionList", questionService.findQuestionByEnabled(true));
        modelAndView.addObject("classList", classOfferedService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/newSurvey", method = RequestMethod.POST)
    public String createSurvey(HttpServletRequest request, Principal principal, Model model) {
        Survey survey = new Survey().bindParam(request);
        if (survey.getQuestions().size() > 0)
            surveyService.save(survey);
        else {
            model.addAttribute("error", "Survey should have at least one question");
            return "redirect:/newSurvey";
        }

        return "redirect:/manageSurvey";
    }

    @RequestMapping(value = "/manageSurvey", method = RequestMethod.GET)
    public String manage(Model model, Principal principal) {
        if (principal == null)
            return "redirect:/login";
        User user = userService.findByUsername(principal.getName());
        if (user.isAdmin())
            model.addAttribute("surveys", surveyService.findAll());
        else
            model.addAttribute("surveys", surveyService.findFacultySurvey(user));
        return "manageSurvey";
    }


    @RequestMapping(value = "/takeSurvey", method = RequestMethod.GET)
    public String takeSurvey(Model model, Principal principal) {
        if (principal == null)
            return "redirect:/login";
        model.addAttribute("surveys", surveyService.findStudentSurvey(principal.getName()));
        return "studentSurvey";
    }

    @RequestMapping(value = "/takeSurvey/{id}", method = RequestMethod.GET)
    public String takeSurvey(@PathVariable("id") int id, Model model, Principal principal) {
        if (principal == null)
            return "redirect:/login";
        Survey survey = surveyService.find(id);
        Student student = studentService.findByUsername(principal.getName());
        if (survey == null || student == null)
            return "redirect:/welcome";
        if (!survey.availableForStudent(student))
            return "redirect:/welcome";
        model.addAttribute("survey", survey);
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
        if (survey != null) {
            Map<Question, SurveyReport> reports = survey.prepareReport();
            model.addAttribute("reports", reports);
        }
        return "surveyDetails";
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable("id") int id, Model model, Principal principal) {
        Survey survey = surveyService.find(id);
        if (survey.getStatus().equals(SurveyStatus.OPENED))
            survey.setStatus(SurveyStatus.CLOSED);
        else
            survey.setStatus(SurveyStatus.OPENED);

        surveyService.save(survey);
        return "redirect:/manageSurvey";
    }


}
