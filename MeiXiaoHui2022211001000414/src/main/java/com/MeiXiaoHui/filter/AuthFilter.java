package com.MeiXiaoHui.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("i am in AuthFilter-->doFilter()- before servlet-(request com here)");
        chain.doFilter(request, response);
        System.out.println("i am in AuthFilter-->doFilter()- after servlet-(request com here)");
    }
}
