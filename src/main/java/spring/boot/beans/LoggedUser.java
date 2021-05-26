package spring.boot.beans;


import spring.boot.service.Admin;

public interface LoggedUser {
    @Admin
    String addQuiz (Quiz quiz);
    @Admin
    String changeQuiz (Quiz quiz);
    @Admin
    String removeQuiz (String name);
    String listUserQuizzes ();
    String selectQuiz(String name, boolean start);
    String setAnswer(String quizName, String questionText, String answerName, String answerText);
}
