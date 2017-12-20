package edu.mum.evalplus.repository;


import edu.mum.evalplus.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findQuestionsByEnabled(Boolean enabled);
}
