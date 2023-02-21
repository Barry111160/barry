package com.musclebeach.question.model;

import java.util.List;
import java.util.Set;


public interface QuestionDAO_interface {
    void insert(QuestionVO questionVO);

    void update(QuestionVO questionVO);

    void delete(Integer questionID);

    QuestionVO findByPrimaryKey(Integer questionID);

    List<QuestionVO> getAll();

    Set<QuestionVO> getQuestionByQuestionTitle(String questionTitle);

    Set<QuestionVO> getQuestionByQuestionContent(String questionContent);
}
