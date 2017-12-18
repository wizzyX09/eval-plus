package edu.mum.evalplus.service;

import edu.mum.evalplus.model.ClassOffered;
import edu.mum.evalplus.model.Student;
import edu.mum.evalplus.model.Survey;
import edu.mum.evalplus.model.SurveyStatus;
import edu.mum.evalplus.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ISurveyServiceImpl implements  ISurveyService{

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private IStudentService studentService;
    @Override
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey find(Integer id) {
        return surveyRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
         surveyRepository.delete(id);
    }

    @Override
    public List<Survey> findStudentSurvey(String username) {
        Student student = studentService.findByUsername(username);
        List<Survey> surveys = new ArrayList<>();
        if (student == null)
            return surveys;
        List<ClassOffered> lectures = student.getClassOfferedSet().stream().filter(lecture -> lecture.getActive()).collect(Collectors.toList());
        Function<List<ClassOffered>, List<Survey>> function = (lects) -> {
            List<Survey> sveys = new ArrayList<>();
            for (ClassOffered lect : lects) {
                for (Survey surv : lect.getSurveys()) {
                    if (surv.getStatus().equals(SurveyStatus.OPENED))
                        sveys.add(surv);
                }
            }
            return sveys;
        };
        return function.apply(lectures);
    }


}
