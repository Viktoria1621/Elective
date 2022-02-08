package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {
    private int answerID;
    private String answerContent;
    private boolean isTrue;
    private int questionID;

    public Answer() {

    }

    public Answer(String answerContent, boolean isTrue, int questionID) {
        this.answerContent = answerContent;
        this.isTrue = isTrue;
        this.questionID = questionID;
    }

    public Answer(int answerID, String answerContent, boolean isTrue, int questionID) {
        this.answerID = answerID;
        this.answerContent = answerContent;
        this.isTrue = isTrue;
        this.questionID = questionID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerID=" + answerID +
                ", answerContent='" + answerContent + '\'' +
                ", isTrue=" + isTrue +
                ", questionID=" + questionID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return answerID == answer.answerID && isTrue == answer.isTrue && questionID == answer.questionID && answerContent.equals(answer.answerContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerID, answerContent, isTrue, questionID);
    }
}
