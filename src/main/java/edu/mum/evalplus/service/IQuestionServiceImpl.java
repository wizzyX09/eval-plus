package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Question;
import edu.mum.evalplus.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IQuestionServiceImpl implements  IQuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question survey) {
        questionRepository.save(survey);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question find(Integer id) {
        return questionRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.delete(id);
    }

}
