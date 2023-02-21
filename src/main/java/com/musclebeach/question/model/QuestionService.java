package com.musclebeach.question.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService {
    @Resource
    private QuestionDAO_interface dao;

    public QuestionVO addQuestion(String questionTitle, String questionContent) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuestionTitle(questionTitle);
        questionVO.setQuestionContent(questionContent);
        dao.insert(questionVO);

        return questionVO;
    }

    public QuestionVO updateQuestion(Integer questionID, String questionTitle, String questionContent) {
        QuestionVO questionVO = new QuestionVO();
        questionVO.setQuestionID(questionID);
        questionVO.setQuestionTitle(questionTitle);
        questionVO.setQuestionContent(questionContent);
        dao.update(questionVO);

        return questionVO;
    }

    public void deletQuestion(Integer questionID) {
        dao.delete(questionID);
    }

    public QuestionVO getOneQuestion(Integer questionID) {
        return dao.findByPrimaryKey(questionID);

    }

    public Set<QuestionVO> getQuestionByQuestionTitle(String questionTitle) {
        return dao.getQuestionByQuestionTitle(questionTitle);

    }

    public Set<QuestionVO> getQuestionByQuestionContent(String questionContent) {
        return dao.getQuestionByQuestionContent(questionContent);

    }

    public List<QuestionVO> getAll() {
        return dao.getAll();
    }
}
