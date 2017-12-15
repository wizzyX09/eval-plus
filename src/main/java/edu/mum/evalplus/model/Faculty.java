package edu.mum.evalplus.model;

import javax.persistence.*;

@Entity
@Table(name="faculties")
public class Faculty {

     @Id
     @GeneratedValue
     private Integer id;

     private String department;

     private String username;

     public Integer getId() {
          return id;
     }

     public String getDepartment() {
          return department;
     }

     public String getUsername() {
          return username;
     }
}
