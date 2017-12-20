package edu.mum.evalplus.service;


import edu.mum.evalplus.model.Faculty;
import edu.mum.evalplus.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IFacultyServiceImpl implements IFacultyService {

    @Autowired
    private FacultyRepository questionRepository;

    @Override
    public void save(Faculty survey) {
        questionRepository.save(survey);
    }

    @Override
    public List<Faculty> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Faculty find(Integer id) {
        return questionRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.delete(id);
    }

    @Override
    public Faculty findFacultyByUsername(String username) {
        return questionRepository.findFacultyByUsername(username);
    }

}
