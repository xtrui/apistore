package cn.xtrui.database.filter;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        String userName = servletRequest.getParameter("userName");
        String passWord = servletRequest.getParameter("passWord");
    }
}
