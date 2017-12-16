package edu.mum.evalplus.repository;


import edu.mum.evalplus.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
