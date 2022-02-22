package by.tc.elective.dao;

import by.tc.elective.bean.Lecture;

import java.util.List;

public interface LectureDAO {
    void initLecture(Lecture newLecture) throws DAOException;

    void deleteLecture(int id) throws DAOException;

    Lecture findLectureByID(int id) throws DAOException;

    List<Lecture> showAllLectures() throws DAOException;

    void updateLecture(Lecture lecture, int id) throws DAOException;
}
