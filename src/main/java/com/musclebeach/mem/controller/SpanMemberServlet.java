package com.musclebeach.mem.controller;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.mem.model.MemService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/spanMember")
public class SpanMemberServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final MemService memService = context.getBean(MemService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberId = Integer.valueOf(request.getParameter("memberId"));

        memService.updateMemStatus(memberId);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("除籍成功！");
    }
}
