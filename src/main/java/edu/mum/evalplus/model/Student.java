package edu.mum.evalplus.model;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
}
