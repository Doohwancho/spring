package com.example.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "koreanServlet", urlPatterns = "/korean")
public class KoreanServlet extends HttpServlet {

    public static final String 인코딩 = "인코딩";

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().log("init() 호출");
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        getServletContext().log("service() 호출");
        response.getWriter().write(인코딩);
    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() 호출");
    }
}
