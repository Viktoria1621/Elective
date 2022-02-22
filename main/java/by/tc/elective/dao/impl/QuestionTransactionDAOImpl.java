package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.QuestionTransactionDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Answer;
import by.tc.elective.bean.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionTransactionDAOImpl implements QuestionTransactionDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_QUESTION_REQUEST = "INSERT INTO questions (content, finalTests_id) VALUES (?, ?)";
    private static final String INIT_ANSWER_REQUEST = "INSERT INTO answers (content, isTrue, questions_id) VALUES (?, ?, ?)";
    private static final String DELETE_QUESTION_BY_ID_REQUEST = "DELETE FROM questions WHERE questions_id = ?";
    private static final String DELETE_ANSWER_BY_ID_REQUEST = "DELETE FROM answers WHERE questions_id = ?";

    @Override
    public void initQuestion(Question newQuestion, Answer newAnswer) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            con.setAutoCommit(false);

            try {
                ps = con.prepareStatement(INIT_QUESTION_REQUEST);

                ps.setString(1, newQuestion.getQuestionContent());
                ps.setInt(2, newQuestion.getFinalTestID());
                ps.executeUpdate();

                ps = con.prepareStatement(INIT_ANSWER_REQUEST);

                ps.setString(1, newAnswer.getAnswerContent());
                ps.setBoolean(2, newAnswer.isTrue());
                ps.setInt(3, newAnswer.getQuestionID());
                ps.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new DAOException("Question isn't added.", e);
            }
        } catch (SQLException e) {
            throw new DAOException("Question isn't added.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteQuestion(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            con.setAutoCommit(false);

            try {
                ps = con.prepareStatement(DELETE_ANSWER_BY_ID_REQUEST);

                ps.setInt(1, id);
                ps.executeUpdate();

                ps = con.prepareStatement(DELETE_QUESTION_BY_ID_REQUEST);

                ps.setInt(1, id);
                ps.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new DAOException("Question isn't deleted.", e);
            }
        } catch (SQLException e) {
            throw new DAOException("Question isn't deleted.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
