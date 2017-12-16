package edu.mum.evalplus.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="faculties")
public class Faculty {

     @Id
     @GeneratedValue
     private Integer id;

     private String department;

     private String username;
     @Embedded
     private Person person;
     @OneToMany(mappedBy = "faculty")
     private Set<ClassOffered> classOfferedSet;

     public Faculty() {
          this.person=new Person();
          classOfferedSet=new HashSet<>();
     }

     public Faculty(String firstName, String lastName, Gender gender, String email, String username) {
          this.username = username;
          this.person=new Person(firstName,lastName,gender,email);
         classOfferedSet=new HashSet<>();

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

     public Person getPerson() {
          return person;
     }

    public Set<ClassOffered> getClassOfferedSet() {
        return Collections.unmodifiableSet(classOfferedSet);
    }


}
