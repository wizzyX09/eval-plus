package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int courseCode;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourseCode() {
        return courseCode;
    }
}
