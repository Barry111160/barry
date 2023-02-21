package com.musclebeach.common.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CartInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object member = request.getSession().getAttribute("memVO");
        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/front-end/member/login.jsp");
            return false;
        }
        return true;
    }
}
