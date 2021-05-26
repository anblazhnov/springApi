package spring.boot.beans;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String text;
    private Type type;
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        TEXT, SINGLE, MULTIPLE
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", answer=" + answers +
                '}';
    }
}