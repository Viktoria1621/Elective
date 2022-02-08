package by.tc.elective.dao;

import by.tc.elective.bean.Answer;

import java.util.List;

public interface AnswerDAO {
    void initAnswer(Answer newAnswer) throws DAOException;
    Answer findAnswerByID(int id) throws DAOException;
    List<Answer> showAllAnswers() throws DAOException;
    void updateAnswer(Answer answer, int id) throws DAOException;
}
