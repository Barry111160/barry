package com.musclebeach.question.model;

import java.io.Serializable;

public class QuestionVO implements Serializable {
    private Integer questionID;
    private String questionTitle;
    private String questionContent;


    public QuestionVO() {
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }


}


