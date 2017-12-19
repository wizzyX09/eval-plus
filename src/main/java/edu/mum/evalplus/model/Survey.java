package edu.mum.evalplus.model;


import edu.mum.evalplus.util.SurveyReport;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "surveys")
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
    private Set<SurveyAnswer> answers;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassOffered classOffered;

    public Survey() {
        this.questions = new HashSet<>();
        this.answers = new HashSet<>();
        this.classOffered = new ClassOffered();
    }

    public Survey(Date createdDate, SurveyStatus status, ClassOffered classOffered) {
        this.createdDate = createdDate;
        this.status = status;
        this.questions = new HashSet<>();
        this.answers = new HashSet<>();
        this.classOffered = classOffered;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question removeQuestion(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    public void addAnswer(SurveyAnswer answer) {
        answer.setSurvey(this);
        answers.add(answer);
    }

    public SurveyAnswer removeAnswer(SurveyAnswer answer) {

        if (answers.remove(answer)) {
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


    public Set<SurveyAnswer> getAnswers() {
        return Collections.unmodifiableSet(answers);
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

    public Map<Question, SurveyReport> prepareReport() {
        Map<Question, SurveyReport> reports = new HashMap<>();
        for (SurveyAnswer surveyAnswer : this.getAnswers()) {
            if (surveyAnswer.getQuestion().getType().equals(QuestionType.MCQ)) {
                if (reports.containsKey(surveyAnswer.getQuestion())) {
                    computeResponse(reports.get(surveyAnswer.getQuestion()), surveyAnswer);
                } else {
                    SurveyReport mcqQuestionReport = new SurveyReport();
                    mcqQuestionReport.setQuestionType(QuestionType.MCQ);
                    mcqQuestionReport.setQuestion(surveyAnswer.getQuestion().getQuestion());
                    reports.put(surveyAnswer.getQuestion(), computeResponse(mcqQuestionReport, surveyAnswer));
                }

            } else {

                if (reports.containsKey(surveyAnswer.getQuestion())) {
                    reports.get(surveyAnswer.getQuestion()).getOpenedAnswers().add(surveyAnswer.getAnswer());
                } else {
                    SurveyReport report = new SurveyReport();
                    report.setQuestionType(QuestionType.OPENED);
                    report.setQuestion(surveyAnswer.getQuestion().getQuestion());
                    report.getOpenedAnswers().add(surveyAnswer.getAnswer());
                    reports.put(surveyAnswer.getQuestion(), report);
                }


            }
        }
        return reports;
    }

    private SurveyReport computeResponse(SurveyReport mcqQuestionReport, SurveyAnswer surveyAnswer) {
        if (surveyAnswer.getAnswer().equalsIgnoreCase("CompletelyAgree"))
            mcqQuestionReport.setCompletelyAgree(mcqQuestionReport.getCompletelyDisagree() + 1);
        if (surveyAnswer.getAnswer().equalsIgnoreCase("Agree"))
            mcqQuestionReport.setAgree(mcqQuestionReport.getAgree() + 1);
        if (surveyAnswer.getAnswer().equalsIgnoreCase("Neutral"))
            mcqQuestionReport.setNeutral(mcqQuestionReport.getNeutral() + 1);
        if (surveyAnswer.getAnswer().equalsIgnoreCase("Disagree"))
            mcqQuestionReport.setDisagree(mcqQuestionReport.getDisagree() + 1);
        if (surveyAnswer.getAnswer().equalsIgnoreCase("CompletelyDisagree"))
            mcqQuestionReport.setCompletelyDisagree(mcqQuestionReport.getCompletelyDisagree() + 1);
        return mcqQuestionReport;
    }

    public Survey prepareAnswers(HttpServletRequest request, Student student) {

        for (Question question : this.getQuestions()) {
            //Retreive param from view
            String answer = request.getParameter(question.getId() + "");

            //SurveyAnswer preparation
            SurveyAnswer surveyAnswer = new SurveyAnswer();
            surveyAnswer.setAnswer(answer);
            surveyAnswer.setQuestion(question);
            surveyAnswer.setStudent(student);
            //Adding SurveyAnswer into Survey
            this.addAnswer(surveyAnswer);

        }
        return this;
    }

    public Survey bindParam(HttpServletRequest request) {
        String[] questionsId = request.getParameterValues("questions");
        String classOfferedId = request.getParameter("classOffered");
        String status = request.getParameter("status");
        ClassOffered classOffered = new ClassOffered();
        Survey survey = new Survey();
        classOffered.setId(Integer.parseInt(classOfferedId));
        survey.setClassOffered(classOffered);
        survey.setStatus(status.equals(SurveyStatus.OPENED) ? SurveyStatus.OPENED : SurveyStatus.CLOSED);
        survey.setCreatedDate(new Date());
        for (int i = 0; i < questionsId.length; i++) {
            Question question = new Question();
            question.setId(Integer.parseInt(questionsId[i]));
            survey.addQuestion(question);
        }
        return survey;
    }
}
