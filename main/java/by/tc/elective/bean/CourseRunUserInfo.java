package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class CourseRunUserInfo implements Serializable {
    private int courseRunUserInfoID;
    private int userID;
    private String courseStatus;
    private int userScore;
    private String teacherComment;
    private int courseRunID;

    public CourseRunUserInfo() {

    }

    public CourseRunUserInfo(int userID, String courseStatus, int userScore, String teacherComment, int courseRunID) {
        this.userID = userID;
        this.courseStatus = courseStatus;
        this.userScore = userScore;
        this.teacherComment = teacherComment;
        this.courseRunID = courseRunID;
    }

    public CourseRunUserInfo(int courseRunUserInfoID, int userID, String courseStatus, int userScore, String teacherComment, int courseRunID) {
        this.courseRunUserInfoID = courseRunUserInfoID;
        this.userID = userID;
        this.courseStatus = courseStatus;
        this.userScore = userScore;
        this.teacherComment = teacherComment;
        this.courseRunID = courseRunID;
    }

    public CourseRunUserInfo(int userID, int courseRunID) {
        this.userID = userID;
        this.courseRunID = courseRunID;
    }

    public int getCourseRunUserInfoID() {
        return courseRunUserInfoID;
    }

    public void setCourseRunUserInfoID(int courseRunUserInfoID) {
        this.courseRunUserInfoID = courseRunUserInfoID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public int getCourseRunID() {
        return courseRunID;
    }

    public void setCourseRunID(int courseRunID) {
        this.courseRunID = courseRunID;
    }

    public boolean isValid() {
        return courseRunUserInfoID != 0 && userID != 0 && courseRunID != 0;
    }

    @Override
    public String toString() {
        return "CourseRunUserInfo{" +
                "courseRunUserInfoID=" + courseRunUserInfoID +
                ", userID=" + userID +
                ", courseStatus='" + courseStatus + '\'' +
                ", userScore=" + userScore +
                ", teacherComment='" + teacherComment + '\'' +
                ", courseRunID=" + courseRunID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseRunUserInfo)) return false;
        CourseRunUserInfo that = (CourseRunUserInfo) o;
        return courseRunUserInfoID == that.courseRunUserInfoID && userID == that.userID && userScore == that.userScore && courseRunID == that.courseRunID && courseStatus.equals(that.courseStatus) && teacherComment.equals(that.teacherComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseRunUserInfoID, userID, courseStatus, userScore, teacherComment, courseRunID);
    }
}
