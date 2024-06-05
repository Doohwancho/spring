package com.cho.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//TODO - k-3. 주의! Application.java에 @ServletComponentScan를 추가하고, 또 CustomFilter에 @WebFilter를 추가하면, 스캔 2번한다!
@WebFilter(urlPatterns = "/filtered/*")
public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터2");
        chain.doFilter(request, response);
    }

}
