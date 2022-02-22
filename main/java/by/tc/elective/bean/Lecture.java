package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class Lecture implements Serializable {
    private int lectureID;
    private String lectureTitle;
    private String theoryPath;
    private int courseID;

    public Lecture() {

    }

    public Lecture(String lectureTitle, String theoryPath, int courseID) {
        this.lectureTitle = lectureTitle;
        this.theoryPath = theoryPath;
        this.courseID = courseID;
    }

    public Lecture(int lectureID, String lectureTitle, String theoryPath, int courseID) {
        this.lectureID = lectureID;
        this.lectureTitle = lectureTitle;
        this.theoryPath = theoryPath;
        this.courseID = courseID;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getTheoryPath() {
        return theoryPath;
    }

    public void setTheoryPath(String theoryPath) {
        this.theoryPath = theoryPath;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureID=" + lectureID +
                ", lectureTitle='" + lectureTitle + '\'' +
                ", theoryPath='" + theoryPath + '\'' +
                ", courseID=" + courseID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecture)) return false;
        Lecture lecture = (Lecture) o;
        return lectureID == lecture.lectureID && courseID == lecture.courseID && lectureTitle.equals(lecture.lectureTitle) && theoryPath.equals(lecture.theoryPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lectureID, lectureTitle, theoryPath, courseID);
    }
}
