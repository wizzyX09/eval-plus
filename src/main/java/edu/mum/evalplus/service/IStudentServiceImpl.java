package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Role;
import edu.mum.evalplus.model.Student;
import edu.mum.evalplus.model.User;
import edu.mum.evalplus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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
    private final String DEFAULT_PASSWORD = "$2a$11$X2.qF1gFgCbpTa1eAo4gO.kAKaVRvBwl/26Ckk2Ph0S2S6tdxKs/O";


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
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setUsername(student.getFirstName().toLowerCase());
        user.setPassword(DEFAULT_PASSWORD);
        student.setUsername(student.getFirstName().toLowerCase());
        studentRepository.save(student);
        for (Role rol : user.getRoles()) {
            if (!role.getName().equals("ROLE_STUDENT")) {
                user.getRoles().remove(rol);
            }
        }
        userService.save(user);
        emailService.sendMail(student.getEmail(), "Survey System credentials", "Welcome to Eval Plus!!  " +
                "We  created an account for you: username: " + user.getUsername().toLowerCase() + " and password:wisleo ");
    }
}
