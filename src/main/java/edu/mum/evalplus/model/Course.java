package edu.mum.evalplus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String code;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<ClassOffered> classOfferedList;

    public Course() {
        classOfferedList = new ArrayList<>();
    }

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
        classOfferedList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ClassOffered> getClassOfferedList() {
        return Collections.unmodifiableList(classOfferedList);
    }


}
