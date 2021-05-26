package spring.boot.beans;

import java.util.Date;

public class UserQuiz {
    private Date start;
    private Date end;
    private Quiz quiz;

    public UserQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
