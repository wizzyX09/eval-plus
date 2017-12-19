package edu.mum.evalplus.web;


import edu.mum.evalplus.model.*;
import edu.mum.evalplus.service.*;
import edu.mum.evalplus.util.McqQuestionReport;
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
import java.util.List;
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
    public ModelAndView surveyForm() {
        ModelAndView modelAndView = new ModelAndView("surveyForm");
        modelAndView.addObject("questionList", questionService.findAll());
        modelAndView.addObject("classList", classOfferedService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/newSurvey", method = RequestMethod.POST)
    public String createSurvey(@RequestParam("status") SurveyStatus status, @RequestParam("classOffered") Integer id,
                               @RequestParam("questions") Integer[] questionsId) {
        ClassOffered classOffered = new ClassOffered();
        Survey survey = new Survey();
        classOffered.setId(id);
        survey.setClassOffered(classOffered);
        for (int i = 0; i < questionsId.length; i++) {
            Question question = new Question();
            question.setId(questionsId[i]);
            survey.addQuestion(question);
        }
        surveyService.save(survey);
        emailService.sendMail("ebayarkhuu@mum.edu", "Survey", "A new survey is created");
        return "redirect:/manageSurvey";
    }

    @RequestMapping(value = "/manageSurvey", method = RequestMethod.GET)
    public ModelAndView manage(Model model) {
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
    public String takeSurvey(@PathVariable("id") int id, Model model) {
        model.addAttribute("survey", surveyService.find(id));
        return "takeSurvey";
    }

    @RequestMapping(value = "/takeSurvey", method = RequestMethod.POST)
    public String saveSurvey(@RequestParam("surveyId") Integer surveyId, HttpServletRequest request, Model model, Principal principal) {
        Survey survey = surveyService.find(surveyId);
        for (int i = 0; i < survey.getQuestions().size(); i++) {
            //Retreive param from view
            String param = "quest" + (i + 1);
            String value = request.getParameter(param);
            String values[] = value.split(",");
            Integer questionId = Integer.parseInt(values[0]);
            String answer = values[1];
            //Question preparation
            Question question = new Question();
            question.setId(questionId);
            //SurveyAnswer preparation
            SurveyAnswer surveyAnswer = new SurveyAnswer();
            surveyAnswer.setAnswer(answer);
            surveyAnswer.setQuestion(question);
            surveyAnswer.setStudent(studentService.findByUsername(principal.getName()));
            //Adding SurveyAnswer into Survey
            survey.addAnswer(surveyAnswer);

        }

        surveyService.save(survey);
        return "home";
    }


    @RequestMapping(value = "/surveyDetails/{id}", method = RequestMethod.GET)
    public String surveyDetails(@PathVariable("id") int id, Model model) {
        Survey survey = surveyService.find(id);
        List<Map<Question, McqQuestionReport>> reports = survey.prepareReport();
        Map<Question, McqQuestionReport> mcqReports = reports.get(0);
        mcqReports.forEach((key, quest) -> System.out.println(quest.getQuestion()));

        model.addAttribute("mcqReports", mcqReports);
        // model.addAttribute("openedReports", objects[1]);
        return "surveyDetails";
    }


}
