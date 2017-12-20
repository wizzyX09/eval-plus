package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Faculty;

import java.util.List;

public interface IFacultyService {
    public void save(Faculty question);

    List<Faculty> findAll();

    Faculty find(Integer id);

    void delete(Integer id);

    Faculty findFacultyByUsername(String username);
}
