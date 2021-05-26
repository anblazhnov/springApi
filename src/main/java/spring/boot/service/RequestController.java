package spring.boot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.beans.*;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


@RestController
public class RequestController {
    App app = App.getApp();
    ClassLoader classLoader = app.getClass().getClassLoader();
    Class<?>[] interfaces = app.getClass().getInterfaces();
    LoginInvocationHandler admin = new LoginInvocationHandler(app);
    LoggedUser loggedUser = (LoggedUser) Proxy.newProxyInstance(classLoader, interfaces, admin);
    @RequestMapping("/login")
    public String login (@RequestParam("id") String userId) {
        app.login(userId);
        return "logged in as "+ app.getUser().getUserType();
    }
    @RequestMapping("/add")
    public String add (@RequestBody String json) {
        Quiz quiz = getQuiz(json);
        if (quiz == null) return "bad request";
        return loggedUser.addQuiz(quiz);
    }
    @RequestMapping("/change")
    public String change (@RequestBody String json) {
        Quiz quiz = getQuiz(json);
        if (quiz == null) return "bad request";
        return loggedUser.changeQuiz(quiz);
    }
    @RequestMapping("/remove")
    public String remove (@RequestParam("name") String quizName) {
        return loggedUser.removeQuiz(quizName);
    }
    @RequestMapping("/list/user")
    public String listUser () {
        return loggedUser.listUserQuizzes();
    }
    @RequestMapping("/list/all")
    public String listAll () {
        return app.getQuizzes().toString();
    }
    @RequestMapping("/selectquiz")
    public String selectQuiz (
            @RequestParam("name") String quizName,
            @RequestParam(value="start", required = false, defaultValue = "true") boolean start
    ) {
        return loggedUser.selectQuiz(quizName, start);
    }
    @RequestMapping("/setanswer")
    public String setAnswer (
            @RequestParam("name") String quizName,
            @RequestParam("question") String question,
            @RequestParam("answer") String answer,
            @RequestParam(value="answerText", required = false, defaultValue = "") String answerText
    ) {
        return loggedUser.setAnswer(quizName, question, answer, answerText);
    }

    private Quiz getQuiz (String json) {
        ObjectMapper mapper = new ObjectMapper();
        Quiz quiz;
        try {
            quiz = mapper.readValue(json, Quiz.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return quiz;
    }
}
