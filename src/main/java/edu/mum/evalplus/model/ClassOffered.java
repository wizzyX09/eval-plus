package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classes-offered")
public class ClassOffered {
    @Id
    @GeneratedValue
    private Integer id;

}
