package edu.mum.evalplus.repository;

import edu.mum.evalplus.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Faculty findFacultyByUsername(String username);
}
