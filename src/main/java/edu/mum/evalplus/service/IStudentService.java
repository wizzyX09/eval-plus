package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Student;

import java.util.List;

public interface IStudentService {
    public void save(Student Student);

    List<Student> findAll();

    Student find(Integer id);

    void delete(Integer id);

    Student findByUsername(String username);
}
