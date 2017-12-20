package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Role;
import edu.mum.evalplus.model.User;
import edu.mum.evalplus.repository.RoleRepository;
import edu.mum.evalplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userService;
    @Autowired
    private RoleRepository roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role role = roleService.findByName("ROLE_ADMIN");
            user.getRoles().add(role);
        }
        userService.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public User findById(Long id) {
        return userService.findOne(id);
    }


}
