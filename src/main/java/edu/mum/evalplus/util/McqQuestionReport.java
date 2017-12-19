package edu.mum.evalplus.util;

public class McqQuestionReport {

    private String question;
    private Integer completelyAgree;
    private Integer agree;
    private Integer neutral;
    private Integer disagree;
    private Integer completelyDisagree;

    public McqQuestionReport() {
        this.completelyAgree = 0;
        this.agree = 0;
        this.neutral = 0;
        this.disagree = 0;
        this.completelyDisagree = 0;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getCompletelyAgree() {
        return completelyAgree;
    }

    public void setCompletelyAgree(Integer completelyAgree) {
        this.completelyAgree = completelyAgree;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getNeutral() {
        return neutral;
    }

    public void setNeutral(Integer neutral) {
        this.neutral = neutral;
    }

    public Integer getDisagree() {
        return disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public Integer getCompletelyDisagree() {
        return completelyDisagree;
    }

    public void setCompletelyDisagree(Integer completelyDisagree) {
        this.completelyDisagree = completelyDisagree;
    }
}
