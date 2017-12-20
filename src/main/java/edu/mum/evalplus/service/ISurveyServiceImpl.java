package edu.mum.evalplus.service;

import edu.mum.evalplus.model.*;
import edu.mum.evalplus.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class ISurveyServiceImpl implements  ISurveyService{

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IFacultyService facultyService;
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
                    if (surv.getStatus().equals(SurveyStatus.OPENED) && !alreadyTook(student, surv))
                        sveys.add(surv);
                }
            }
            return sveys;
        };
        return function.apply(lectures);
    }

    private boolean alreadyTook(Student student, Survey surv) {
        for (SurveyAnswer answer : surv.getAnswers()) {
            if (answer.getStudent().getId() == student.getId())
                return true;
        }
        return false;
    }

    @Override
    public List<Survey> findFacultySurvey(User user) {
        Faculty faculty = facultyService.findFacultyByUsername(user.getUsername());
        List<Survey> surveys = new ArrayList<>();
        if (faculty == null)
            return surveys;
        List<ClassOffered> lectures = faculty.getClassOfferedSet().stream().filter(lecture -> lecture.getActive()).collect(Collectors.toList());
        Function<List<ClassOffered>, List<Survey>> function = (lects) -> {
            List<Survey> sveys = new ArrayList<>();
            for (ClassOffered lect : lects) {
                for (Survey surv : lect.getSurveys()) {
                        sveys.add(surv);
                }
            }
            return sveys;
        };
        return function.apply(lectures);
    }


}
