package com.example.nextstep.controller;

import com.example.nextstep.db.Database;
import com.example.nextstep.model.User;
import com.example.nextstep.webserver.http.HttpRequest;
import com.example.nextstep.webserver.http.HttpResponse;

import java.util.Collection;

public class ListUserController extends AbstractController{

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (!isLogin(httpRequest)) { //쿠키 확인해서 verify()된 경우만 pass
            httpResponse.sendRedirect("/user/login_failed.html");
            return;
        }

        StringBuilder builder = new StringBuilder("<html>");
        builder.append("<body>");
        builder.append("<table border='1'>");
        Collection<User> all = Database.findAll();
        for (User user : all) {
            builder.append("<tr>");
            builder.append("<td>" + user.getUserId() + "</td>");
            builder.append("<td>" + user.getName() + "</td>");
            builder.append("<td>" + user.getEmail() + "</td>");
            builder.append("</tr>");
        }
        builder.append("</table>");
        builder.append("</body>");
        builder.append("</html>");

        httpResponse.forwardBody(httpRequest, builder.toString());
    }

    private boolean isLogin(HttpRequest httpRequest) {
        return httpRequest.isLogin();
    }
}
