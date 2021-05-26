package spring.boot.beans;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private final UserType userType;
    private List<UserQuiz> quizzes = new ArrayList<>();

    public User (UserType userType, String id) {
        this.userType = userType;
        this.id = id;
    }

    public List<UserQuiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<UserQuiz> quizzes) {
        this.quizzes = quizzes;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getId() {
        return id;
    }


    public enum UserType {
        ADMIN, USER, ANONYMOUS
    }
}
