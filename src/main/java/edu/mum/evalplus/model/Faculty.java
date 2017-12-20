package edu.mum.evalplus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="faculties")
public class Faculty implements Serializable {

     @Id
     @GeneratedValue
     private Integer id;

     private String department;

     private String username;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    @OneToMany(mappedBy = "faculty")
    private List<ClassOffered> classOfferedSet;

     public Faculty() {
         classOfferedSet = new ArrayList<>();
     }

     public Faculty(String firstName, String lastName, Gender gender, String email, String username) {
          this.username = username;
         classOfferedSet = new ArrayList<>();

     }

     public void addClass(ClassOffered classOffered){
         classOfferedSet.add(classOffered);
         classOffered.setFaculty(this);
     }
     public Boolean removeClass(ClassOffered classOffered){
         classOffered.setFaculty(null);
         return classOfferedSet.remove(classOffered);
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public String getDepartment() {
          return department;
     }

     public void setDepartment(String department) {
          this.department = department;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

    public List<ClassOffered> getClassOfferedSet() {
        return Collections.unmodifiableList(classOfferedSet);
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

    public void setClassOfferedSet(List<ClassOffered> classOfferedSet) {
        this.classOfferedSet = classOfferedSet;
    }
}
