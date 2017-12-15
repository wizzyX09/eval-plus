package edu.mum.evalplus.model;

import javax.persistence.*;

@Entity
@Table(name="faculties")
public class Faculty {

     @Id
     @GeneratedValue
     private Integer id;
}
