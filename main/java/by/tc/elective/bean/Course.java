package by.tc.elective.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Course implements Serializable {
    private int courseID;
    private String courseTitle;
    private String courseDescription;
    private int courseDuration;
    private int categoryID;
    private int finalTestID;
    private int teacherID;

    public Course(){

    }

    public Course(String courseTitle, String courseDescription, int courseDuration, int categoryID, int finalTestID, int teacherID) {
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
        this.categoryID = categoryID;
        this.finalTestID = finalTestID;
        this.teacherID = teacherID;
    }

    public Course(int courseID, String courseTitle, String courseDescription, int courseDuration, int categoryID, int finalTestID, int teacherID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
        this.categoryID = categoryID;
        this.finalTestID = finalTestID;
        this.teacherID = teacherID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getFinalTestID() {
        return finalTestID;
    }

    public void setFinalTestID(int finalTestID) {
        this.finalTestID = finalTestID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public boolean isValid() {
        return courseID != 0 && courseTitle != null && courseDescription != null && courseDuration != 0 && categoryID != 0 && finalTestID != 0 && teacherID != 0;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseDuration=" + courseDuration +
                ", categoryID=" + categoryID +
                ", finalTestID=" + finalTestID +
                ", teacherID=" + teacherID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return courseID == course.courseID && courseDuration == course.courseDuration && categoryID == course.categoryID && finalTestID == course.finalTestID && teacherID == course.teacherID && courseTitle.equals(course.courseTitle) && courseDescription.equals(course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseTitle, courseDescription, courseDuration, categoryID, finalTestID, teacherID);
    }
}
