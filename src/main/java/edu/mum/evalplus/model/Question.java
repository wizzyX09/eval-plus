package edu.mum.evalplus.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String question ;
    @Enumerated
    private QuestionType type;
    private Boolean enabled;

    public Question() {
    }

    public Question(String question, QuestionType type, Boolean enabled) {
        this.question = question;
        this.type = type;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
