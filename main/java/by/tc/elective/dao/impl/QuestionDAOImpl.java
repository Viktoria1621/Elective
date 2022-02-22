package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.QuestionDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_QUESTION_BY_ID_REQUEST = "SELECT * FROM questions WHERE questions_id = ?";
    private static final String SELECT_ALL_QUESTIONS_REQUEST = "SELECT * FROM questions";
    private static final String UPDATE_QUESTION_BY_ID_REQUEST = "UPDATE questions SET content = ?, finalTests_id = ? WHERE questions_id = ?";

    @Override
    public Question findQuestionByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Question question = new Question();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_QUESTION_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                question.setQuestionID(rs.getInt("questions_id"));
                question.setQuestionContent(rs.getString("content"));
                question.setFinalTestID(rs.getInt("finalTests_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Question isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return question;
    }

    @Override
    public List<Question> showAllQuestions() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Question> question = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_QUESTIONS_REQUEST);

            while (rs.next()) {
                Question newQuestion = new Question();

                newQuestion.setQuestionID(rs.getInt("questions_id"));
                newQuestion.setQuestionContent(rs.getString("content"));
                newQuestion.setFinalTestID(rs.getInt("finalTests_id"));

                question.add(newQuestion);
            }
        } catch (SQLException e) {
            throw new DAOException("Questions aren't found.", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return question;
    }

    @Override
    public void updateQuestion(Question question, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_QUESTION_BY_ID_REQUEST);

            ps.setString(1, question.getQuestionContent());
            ps.setInt(2, question.getFinalTestID());
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Question isn't updated.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
