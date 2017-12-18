package edu.mum.evalplus.repository;

import edu.mum.evalplus.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username);

    @Query("from Student s where s.username =:username ")
    List<Student> findStudentWithNoAccount(@Param("username") String username);
}
