package edu.mum.evalplus.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="surveys")
public class Survey implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    private Date openDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Boolean resubmissionAllowed;
    @Enumerated
    private SurveyStatus status;

    @ManyToMany
    @JoinTable(name = "survey_question", joinColumns = @JoinColumn(name = "survey_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyAnswer> answers;

    @ManyToOne
    @JoinColumn(name="class_id")
    private ClassOffered classOffered;

    public Survey() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.classOffered = new ClassOffered();
    }

    public Survey(Date createdDate, Date openDate, Date endDate, Boolean resubmissionAllowed, SurveyStatus status, ClassOffered classOffered) {
        this.createdDate = createdDate;
        this.openDate = openDate;
        this.endDate = endDate;
        this.resubmissionAllowed = resubmissionAllowed;
        this.status = status;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.classOffered = classOffered;
    }

   public void addQuestion(Question question){
        questions.add(question);
   }
   public Question removeQuestion(Question question){
      if(questions.remove(question)){
          return question;
      }
      return null;
   }

    public void addAnswer(SurveyAnswer answer){
       answer.setSurvey(this);
        answers.add(answer);
    }
    public SurveyAnswer removeAnswer(SurveyAnswer answer){

        if(answers.remove(answer)){
            answer.setSurvey(null);
            return answer;
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getResubmissionAllowed() {
        return resubmissionAllowed;
    }

    public void setResubmissionAllowed(Boolean resubmissionAllowed) {
        this.resubmissionAllowed = resubmissionAllowed;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }


    public List<SurveyAnswer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }


    public ClassOffered getClassOffered() {
        return classOffered;
    }

    public void setClassOffered(ClassOffered classOffered) {
        this.classOffered = classOffered;
    }


}
