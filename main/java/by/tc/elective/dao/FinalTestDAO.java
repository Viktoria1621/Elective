package by.tc.elective.dao;

import by.tc.elective.bean.FinalTest;

import java.util.List;

public interface FinalTestDAO {
    void initTest(FinalTest newFinalTest) throws DAOException;

    void deleteTest(int id) throws DAOException;

    FinalTest findTestByID(int id) throws DAOException;

    List<FinalTest> showAllTests() throws DAOException;

    void updateTest(FinalTest test, int id) throws DAOException;
}
