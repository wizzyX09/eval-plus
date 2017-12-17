package edu.mum.evalplus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="students")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    @Embedded
    private Person person;
    @ManyToMany
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<ClassOffered> classOfferedSet;

    public Student() {
        this.person=new Person();
        this.classOfferedSet = new ArrayList<>();
    }

    public Student(String firstName, String lastName, Gender gender, String email,String username) {
        this.username = username;
        this.person=new Person(firstName,lastName,gender,email);
        this.classOfferedSet = new ArrayList<>();
    }

    public void addClass(ClassOffered classOffered){
        this.classOfferedSet.add(classOffered);
    }

    public ClassOffered removeClass(ClassOffered classOffered){
        if(classOfferedSet.remove(classOffered)){
            return  classOffered;
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getPerson() {
        return person;
    }


    public List<ClassOffered> getClassOfferedSet() {
        return Collections.unmodifiableList(classOfferedSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
