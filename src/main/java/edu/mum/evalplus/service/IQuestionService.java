package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Question;

import java.util.List;

public interface IQuestionService {
    public void save(Question question);
    List<Question> findAll();
    Question find(Integer id);
    void delete(Integer id);
}
