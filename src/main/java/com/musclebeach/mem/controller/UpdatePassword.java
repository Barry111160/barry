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
import java.io.PrintWriter;

@WebServlet("/updatePassword")
public class UpdatePassword extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final MemService memService = context.getBean(MemService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer memberId = Integer.valueOf(request.getParameter("memberId"));
        String newPassword = request.getParameter("newPassword");

        Boolean updatePassword = memService.updatePassWord(memberId, newPassword);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        if (updatePassword) {
            System.out.println(updatePassword);
            writer.println("密碼變更成功！");
        } else {
            System.out.println(updatePassword);
            writer.println("密碼變更失敗");
        }
    }
}
