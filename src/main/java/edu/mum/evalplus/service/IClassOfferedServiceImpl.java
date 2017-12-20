package edu.mum.evalplus.service;

import edu.mum.evalplus.model.ClassOffered;
import edu.mum.evalplus.repository.ClassOfferedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IClassOfferedServiceImpl implements  IClassOfferedService{

    @Autowired
    private ClassOfferedRepository ClassOfferedRepository;

    @Override
    public void save(ClassOffered classOffered) {
        ClassOfferedRepository.save(classOffered);
    }

    @Override
    public List<ClassOffered> findAll() {
        return ClassOfferedRepository.findAll();
    }

    @Override
    public ClassOffered find(Integer id) {
        return ClassOfferedRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        ClassOfferedRepository.delete(id);
    }

}
