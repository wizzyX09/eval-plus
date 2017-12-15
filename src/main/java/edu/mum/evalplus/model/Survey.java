package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="surveys")
public class Survey {
    @Id
    @GeneratedValue
    private Integer id;
}
