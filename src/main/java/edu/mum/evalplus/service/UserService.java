package edu.mum.evalplus.service;


import edu.mum.evalplus.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();
}
