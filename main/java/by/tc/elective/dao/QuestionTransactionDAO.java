package by.tc.elective.dao;

import by.tc.elective.bean.Answer;
import by.tc.elective.bean.Question;

public interface QuestionTransactionDAO {
    void initQuestion(Question newQuestion, Answer newAnswer) throws DAOException;

    void deleteQuestion(int id) throws DAOException;
}
