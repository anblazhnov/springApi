package spring.boot.beans;

import spring.boot.service.Admin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoginInvocationHandler implements InvocationHandler {
    private App app;

    public LoginInvocationHandler(App app) {
        this.app = app;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (app.getUser() == null) return "need to login first";

        if (method.isAnnotationPresent(Admin.class)) {
            System.out.println(app.getUser().getUserType());
            if (app.getUser().getUserType() != User.UserType.ADMIN) {
                return "permission denied";
            }
        }
        return method.invoke(app, args);
    }
}
