package by.tc.elective.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class FinalTest implements Serializable {
    private int finalTestID;
    private String finalTestTitle;
    private String finalTestDescription;
    private Time finalTestDuration;

    public FinalTest() {

    }

    public FinalTest(String finalTestTitle, String finalTestDescription, Time finalTestDuration) {
        this.finalTestTitle = finalTestTitle;
        this.finalTestDescription = finalTestDescription;
        this.finalTestDuration = finalTestDuration;
    }

    public FinalTest(int finalTestID, String finalTestTitle, String finalTestDescription, Time finalTestDuration) {
        this.finalTestID = finalTestID;
        this.finalTestTitle = finalTestTitle;
        this.finalTestDescription = finalTestDescription;
        this.finalTestDuration = finalTestDuration;
    }

    public int getFinalTestID() {
        return finalTestID;
    }

    public void setFinalTestID(int finalTestID) {
        this.finalTestID = finalTestID;
    }

    public String getFinalTestTitle() {
        return finalTestTitle;
    }

    public void setFinalTestTitle(String finalTestTitle) {
        this.finalTestTitle = finalTestTitle;
    }

    public String getFinalTestDescription() {
        return finalTestDescription;
    }

    public void setFinalTestDescription(String finalTestDescription) {
        this.finalTestDescription = finalTestDescription;
    }

    public Time getFinalTestDuration() {
        return finalTestDuration;
    }

    public void setFinalTestDuration(Time finalTestDuration) {
        this.finalTestDuration = finalTestDuration;
    }

    @Override
    public String toString() {
        return "FinalTest{" +
                "finalTestID=" + finalTestID +
                ", finalTestTitle='" + finalTestTitle + '\'' +
                ", finalTestDescription='" + finalTestDescription + '\'' +
                ", finalTestDuration=" + finalTestDuration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalTest)) return false;
        FinalTest finalTest = (FinalTest) o;
        return finalTestID == finalTest.finalTestID && finalTestTitle.equals(finalTest.finalTestTitle) && finalTestDescription.equals(finalTest.finalTestDescription) && finalTestDuration.equals(finalTest.finalTestDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalTestID, finalTestTitle, finalTestDescription, finalTestDuration);
    }
}
