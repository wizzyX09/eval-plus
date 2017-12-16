package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Survey;
import edu.mum.evalplus.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ISurveyServiceImpl implements  ISurveyService{

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
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

}
