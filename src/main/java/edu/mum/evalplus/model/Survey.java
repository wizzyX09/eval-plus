package edu.mum.evalplus.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="surveys")
public class Survey implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Enumerated
    private SurveyStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "survey_question", joinColumns = @JoinColumn(name = "survey_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;


    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SurveyAnswer> answers;

    @ManyToOne
    @JoinColumn(name="class_id")
    private ClassOffered classOffered;

    public Survey() {
        this.questions = new HashSet<>();
        this.answers = new ArrayList<>();
        this.classOffered = new ClassOffered();
    }

    public Survey(Date createdDate, SurveyStatus status, ClassOffered classOffered) {
        this.createdDate = createdDate;
        this.status = status;
        this.questions = new HashSet<>();
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

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public Set<Question> getQuestions() {
        return Collections.unmodifiableSet(questions);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        return getId().equals(survey.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
