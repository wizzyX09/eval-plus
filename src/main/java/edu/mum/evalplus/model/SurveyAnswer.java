package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="survey-answers")
public class SurveyAnswer {
    @Id
    @GeneratedValue
    private Integer id;
}
