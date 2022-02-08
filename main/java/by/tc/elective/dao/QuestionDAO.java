package by.tc.elective.dao;

import by.tc.elective.bean.Question;

import java.util.List;

public interface QuestionDAO {
    Question findQuestionByID(int id) throws DAOException;
    List<Question> showAllQuestions() throws DAOException;
    void updateQuestion(Question question, int id) throws DAOException;
}
