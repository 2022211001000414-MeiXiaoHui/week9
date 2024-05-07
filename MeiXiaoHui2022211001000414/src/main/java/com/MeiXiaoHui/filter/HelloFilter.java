package com.MeiXiaoHui.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "HelloFilter",urlPatterns = {"/*"})
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("i am in HelloFilter-->init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("i am in HelloServlet-doFilter()-return back from go to servlet");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("i am in HelloServlet-doFilter()-after go to servlet");
    }

    @Override
    public void destroy() {
        System.out.println("i am int HelloFilter-->destroy()");
    }
}
