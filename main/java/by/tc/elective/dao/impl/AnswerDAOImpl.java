package by.tc.elective.dao.impl;

import by.tc.elective.dao.DAOException;
import by.tc.elective.dao.AnswerDAO;
import by.tc.elective.dao.pool.ConnectionPool;
import by.tc.elective.bean.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INIT_REQUEST = "INSERT INTO answers (content, isTrue, questions_id) VALUES (?, ?, ?)";
    private static final String FIND_ANSWER_BY_ID_REQUEST = "SELECT * FROM answers WHERE answers_id = ?";
    private static final String SELECT_ALL_ANSWERS_REQUEST = "SELECT * FROM answers";
    private static final String UPDATE_ANSWER_BY_ID_REQUEST = "UPDATE answers SET content = ?, isTrue = ? WHERE answers_id = ?";


    @Override
    public void initAnswer(Answer newAnswer) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INIT_REQUEST);

            ps.setString(1, newAnswer.getAnswerContent());
            ps.setBoolean(2, newAnswer.isTrue());
            ps.setInt(3, newAnswer.getQuestionID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Answer isn't added.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public Answer findAnswerByID(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Answer answer = new Answer();

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(FIND_ANSWER_BY_ID_REQUEST);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                answer.setAnswerID(rs.getInt("answers_id"));
                answer.setAnswerContent(rs.getString("content"));
                answer.setTrue(rs.getBoolean("isTrue"));
            }
        } catch (SQLException e) {
            throw new DAOException("Answer isn't found.", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return answer;
    }

    @Override
    public List<Answer> showAllAnswers() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<Answer> answer = new ArrayList<>();

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_ANSWERS_REQUEST);

            while (rs.next()) {
                Answer newAnswer = new Answer();

                newAnswer.setAnswerID(rs.getInt("answers_id"));
                newAnswer.setAnswerContent(rs.getString("content"));
                newAnswer.setTrue(rs.getBoolean("isTrue"));

                answer.add(newAnswer);
            }
        } catch (SQLException e) {
            throw new DAOException("Answers aren't found.", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
        return answer;
    }

    @Override
    public void updateAnswer(Answer answer, int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_ANSWER_BY_ID_REQUEST);

            ps.setString(1, answer.getAnswerContent());
            ps.setBoolean(2, answer.isTrue());
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Answer isn't updated.", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
