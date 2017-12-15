package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue
    private Integer id;
}
