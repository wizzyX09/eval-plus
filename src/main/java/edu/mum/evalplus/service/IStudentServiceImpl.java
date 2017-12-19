package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Student;
import edu.mum.evalplus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IStudentServiceImpl implements IStudentService {


    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student find(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.delete(id);
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public List<Student> findStudentWithNoAccount() {
        List<Student> list = new ArrayList<>();
        list.addAll(studentRepository.findStudentWithNoAccount());
        return list;
    }


}
