package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Survey;

import java.util.List;

public interface ISurveyService {
    public void save(Survey survey);
    List<Survey> findAll();
    Survey find(Integer id);
    void delete(Integer id );

    List<Survey> findStudentSurvey(String username);
}
