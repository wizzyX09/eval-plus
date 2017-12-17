package edu.mum.evalplus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="classes_offered")
public class ClassOffered implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Boolean active;
    private String name;

    @ManyToMany(mappedBy = "classOfferedSet", fetch = FetchType.EAGER)
    private List<Student> students;

    @OneToMany(mappedBy = "classOffered", fetch = FetchType.EAGER)
    private List<Survey> surveys;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Faculty faculty;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public ClassOffered() {
        this.students = new ArrayList<>();
        this.surveys = new ArrayList<>();
        this.course=new Course();
        this.faculty=new Faculty();
    }

    public ClassOffered(String name, Faculty faculty, Course course) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.students = new ArrayList<>();
        this.surveys = new ArrayList<>();
    }

    public void addStudent(Student student){
        student.getClassOfferedSet().add(this);
        this.students.add(student);
    }
    public void removeStudent(Student student){
        student.getClassOfferedSet().remove(this);
        this.students.remove(student);
    }


    public void addSurvey(Survey survey){
        this.surveys.add(survey);
        survey.setClassOffered(this);
    }

    public Boolean removeSurvey(Survey survey){
        survey.setClassOffered(null);
       return this.surveys.remove(survey); }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Survey> getSurveys() {
        return Collections.unmodifiableList(surveys);
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }


}
