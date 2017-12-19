package edu.mum.evalplus.service;

import edu.mum.evalplus.model.Role;

public interface IRoleService {
    Role findByName(String name);
}
