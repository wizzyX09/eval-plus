package edu.mum.evalplus.model;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="classes_offered")
public class ClassOffered {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

@ManyToMany(mappedBy = "classOfferedSet")
    private Set<Student> students;

    @OneToMany(mappedBy = "classOffered")
    private Set<Survey> surveys;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public ClassOffered() {
        this.students=new HashSet<>();
        this.surveys=new HashSet<>();
        this.course=new Course();
        this.faculty=new Faculty();
    }

    public ClassOffered(String name, Faculty faculty, Course course) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        surveys=new HashSet<>();
        students=new HashSet<>();
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

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public Set<Survey> getSurveys() {
        return Collections.unmodifiableSet(surveys);
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
}
