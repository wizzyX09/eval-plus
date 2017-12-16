package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Survey;
import edu.mum.evalplus.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ISurveyService {
    public void save(Survey survey);
    List<Survey> findAll();
    Survey find(Integer id);
    void delete(Integer id );
}
