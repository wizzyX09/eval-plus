package edu.mum.evalplus.service;

import edu.mum.evalplus.model.ClassOffered;

import java.util.List;

public interface IClassOfferedService {
    public void save(ClassOffered ClassOffered);
    List<ClassOffered> findAll();
    ClassOffered find(Integer id);
    void delete(Integer id);
}
