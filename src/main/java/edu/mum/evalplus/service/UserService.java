package edu.mum.evalplus.service;


import edu.mum.evalplus.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
