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
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
//    @Embedded
//    private Person person;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<ClassOffered> classOfferedSet;

    public Student() {
       // this.person=new Person();
        this.classOfferedSet = new ArrayList<>();
    }

    public Student(String firstName, String lastName, Gender gender, String email,String username) {
        this.username = username;
       // this.person=new Person(firstName,lastName,gender,email);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public Person getPerson() {
      //  return person;
    //}


    public List<ClassOffered> getClassOfferedSet() {
        return Collections.unmodifiableList(classOfferedSet);
    }

}
