package edu.mum.evalplus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="classes-offered")
public class ClassOffered {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Set<Student> students;

    private Set<Survey> surveys;

    private Faculty faculty;

    private Course course;


    public Integer getId() { return id;  }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public void addStudent(Student addStudent){ this.students.add(addStudent); }

    public void removeStudent(Student removeStudent){ this.students.remove(removeStudent); }

    public void addSurvey(Survey addSurvey){this.surveys.add(addSurvey); }

    public void removeSurvey(Survey removeSurvey){ this.surveys.remove(removeSurvey); }

    public ClassOffered(String name, Faculty faculty, Course course) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
    }
}
