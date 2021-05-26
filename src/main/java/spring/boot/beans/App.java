package spring.boot.beans;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App implements LoggedUser {
    private List<Quiz> quizzes = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private User user;

    public static App app;
    private App(){}
    public static synchronized App getApp () {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    {
        users.add(new User(User.UserType.ADMIN, "1"));
        users.add(new User(User.UserType.USER, "2"));
    }

    public void login(String id) {
        this.user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(new User(User.UserType.ANONYMOUS, id));
    }
    public String addQuiz (Quiz quiz) {
        quizzes.add(quiz);
        return "quiz was added";
    }
    public String changeQuiz (Quiz quiz) {
        if (!quizzes.removeIf(q -> q.getName().equals(quiz.getName()))) return "quiz not found";
        quizzes.add(quiz);
        return "quiz was changed";
    }
    public String removeQuiz (String name) {
        if (quizzes.removeIf(q -> q.getName().equals(name)))
            return "quiz was removed";
        return "quiz not found";
    }

    public String listUserQuizzes () {
        StringBuilder sb = new StringBuilder();
        for (UserQuiz quiz : user.getQuizzes()) {
            sb.append("start: ").append(quiz.getStart())
              .append(" end: ").append(quiz.getEnd()).append("\n")
              .append(quiz.getQuiz().printQuiz());
        }
        return sb.toString();
    }
    public String selectQuiz(String name, boolean start) {
        //поиск запроса среди всех запросов
        Quiz quiz = quizzes.stream().filter(q -> q.getName().equals(name)).findAny().orElse(null);
        if (quiz == null) return "quiz not found";
        //поиск запроса у юзера
        UserQuiz userQuiz = user.getQuizzes().stream().filter(q -> q.getQuiz().getName().equals(name)).findAny().orElse(null);
        //Если нужно начать опрос
        if (start) {
            if (userQuiz != null) return "quiz already started";
            UserQuiz newQuiz = new UserQuiz(quiz);
            newQuiz.setStart(new Date());
            this.user.getQuizzes().add(newQuiz);
            return String.format("Quiz %s started by user %s", name, user.getId());
        }
        //Если нужно закончить опрос
        if (userQuiz == null) return "quiz not found";
        if (userQuiz.getEnd() == null) {
            userQuiz.setEnd(new Date());
            return "quiz ended";
        }
        return "quiz was ended before";
    }
    public String setAnswer(String quizName, String questionText, String answerName, String answerText) {
        UserQuiz userQuiz = user.getQuizzes().stream().filter(q -> q.getQuiz().getName().equals(quizName)).findAny().orElse(null);
        if (userQuiz == null) return "quiz not found";
        if (userQuiz.getStart() == null) return "start quiz first";
        if (userQuiz.getEnd() != null) return "quiz ended";
        Question question = userQuiz.getQuiz().getQuestions()
                .stream()
                .filter(q -> q.getText().equals(questionText))
                .findAny()
                .orElse(null);
        if (question == null) return "question not found";
        Answer answer = question.getAnswers().stream().filter(a -> a.getText().equals(answerName)).findAny().orElse(null);
        if (answer == null) return "answer not found";

        //Если ответ выбран - отменить выбор
        if (answer.isSelected()) {
            answer.setSelected(false);
            return "answer "+answer.getText()+" was unselect";
        }
        //Если нельзя выбрать несколько ответов - снять выбор всех предыдущих
        if (question.getType() != Question.Type.MULTIPLE) {
            question.getAnswers().forEach(a -> a.setSelected(false));
        }
        if (question.getType() == Question.Type.TEXT) {
            answer.setAnswerText(answerText);
        }
        answer.setSelected(true);
        return "answer "+answer.getText()+" was selected";
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
