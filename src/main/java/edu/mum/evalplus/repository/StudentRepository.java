package edu.mum.evalplus.repository;

import edu.mum.evalplus.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username);

}
