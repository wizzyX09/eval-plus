package edu.mum.evalplus.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Person {

    private String firstname;
    private String lastname;
}
