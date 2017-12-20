package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Role;
import edu.mum.evalplus.model.Student;
import edu.mum.evalplus.model.User;
import edu.mum.evalplus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IStudentServiceImpl implements IStudentService {


    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IEmailService emailService;


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

    @Override
    public void generateStudentAccount(Integer studentId) {
        Student student = studentRepository.findOne(studentId);
        User user = new User();
        Role role = roleService.findByName("ROLE_STUDENT");
        user.getRoles().add(role);
        user.setUsername(student.getFirstName());
        user.setPassword(encoder.encode(student.getFirstName()));
        student.setUsername(student.getFirstName());
        studentRepository.save(student);
        userService.save(user);
        emailService.sendMail(student.getEmail(), "Survey System credentials", "Welcome to Eval Plus!!  " +
                "We  created an account for you: username: " + user.getUsername() + " and password: " + user.getUsername());
    }
}
