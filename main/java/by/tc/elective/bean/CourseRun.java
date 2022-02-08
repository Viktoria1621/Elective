package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CourseRun implements Serializable {
    private int courseRunID;
    private Date courseStartDay;
    private Date courseEndDay;
    private int numberOfStudentsInGroup;
    private int courseID;

    public CourseRun() {

    }

    public CourseRun(Date courseStartDay, Date courseEndDay, int numberOfStudentsInGroup, int courseID) {
        this.courseStartDay = courseStartDay;
        this.courseEndDay = courseEndDay;
        this.numberOfStudentsInGroup = numberOfStudentsInGroup;
        this.courseID = courseID;
    }

    public CourseRun(int courseRunID, Date courseStartDay, Date courseEndDay, int numberOfStudentsInGroup, int courseID) {
        this.courseRunID = courseRunID;
        this.courseStartDay = courseStartDay;
        this.courseEndDay = courseEndDay;
        this.numberOfStudentsInGroup = numberOfStudentsInGroup;
        this.courseID = courseID;
    }

    public int getCourseRunID() {
        return courseRunID;
    }

    public void setCourseRunID(int courseRunID) {
        this.courseRunID = courseRunID;
    }

    public Date getCourseStartDay() {
        return courseStartDay;
    }

    public void setCourseStartDay(Date courseStartDay) {
        this.courseStartDay = courseStartDay;
    }

    public Date getCourseEndDay() {
        return courseEndDay;
    }

    public void setCourseEndDay(Date courseEndDay) {
        this.courseEndDay = courseEndDay;
    }

    public int getNumberOfStudentsInGroup() {
        return numberOfStudentsInGroup;
    }

    public void setNumberOfStudentsInGroup(int numberOfStudentsInGroup) {
        this.numberOfStudentsInGroup = numberOfStudentsInGroup;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "CourseRun{" +
                "courseRunID=" + courseRunID +
                ", courseStartDay=" + courseStartDay +
                ", courseEndDay=" + courseEndDay +
                ", numberOfStudentsInGroup=" + numberOfStudentsInGroup +
                ", courseID=" + courseID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseRun)) return false;
        CourseRun courseRun = (CourseRun) o;
        return courseRunID == courseRun.courseRunID && numberOfStudentsInGroup == courseRun.numberOfStudentsInGroup && courseID == courseRun.courseID && courseStartDay.equals(courseRun.courseStartDay) && courseEndDay.equals(courseRun.courseEndDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseRunID, courseStartDay, courseEndDay, numberOfStudentsInGroup, courseID);
    }
}
