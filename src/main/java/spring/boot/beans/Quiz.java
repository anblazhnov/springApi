package spring.boot.beans;

import java.util.Date;
import java.util.List;

public class Quiz {
    private String name;
    private String description;
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String printQuiz() {
        return "Quiz{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
