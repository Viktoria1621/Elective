package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private int questionID;
    private String questionContent;
    private int finalTestID;

    public Question() {

    }

    public Question(String questionContent, int finalTestID) {
        this.questionContent = questionContent;
        this.finalTestID = finalTestID;
    }

    public Question(int questionID, String questionContent, int finalTestID) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.finalTestID = finalTestID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getFinalTestID() {
        return finalTestID;
    }

    public void setFinalTestID(int finalTestID) {
        this.finalTestID = finalTestID;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", questionContent='" + questionContent + '\'' +
                ", finalTestID=" + finalTestID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return questionID == question.questionID && finalTestID == question.finalTestID && questionContent.equals(question.questionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionID, questionContent, finalTestID);
    }
}
