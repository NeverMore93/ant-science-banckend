package com.antScience.foundation.filter;

import com.antScience.foundation.common.TokenService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorityFilter extends HttpServlet implements Filter {

    private static final long serialVersionUID = 1L;
    private static final String AUTH = "/auth/";

    private TokenService tokenService = new TokenService();

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sRequest;
        String token = request.getHeader("token");
        String userName = request.getHeader("userName");
        System.out.println("token: " + token);
        System.out.println("userName: " + userName);
        HttpServletResponse response = (HttpServletResponse) sResponse;
        String url = request.getServletPath();
        if (!url.contains(AUTH)) {
            System.out.println("开始拦截验证， 处理Token");
            try {
                token = tokenService.identify(token, userName);
                response.setHeader("token", token);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
            return;
        }
        filterChain.doFilter(request, response);
    }
}
