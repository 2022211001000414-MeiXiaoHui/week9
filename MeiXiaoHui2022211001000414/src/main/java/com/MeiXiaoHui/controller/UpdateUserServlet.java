package com.MeiXiaoHui.controller;

import com.MeiXiaoHui.dao.UserDao;
import com.MeiXiaoHui.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() {
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //write code
        //TODO 1: forward to WEB-INF/views/updateUser.jsp
        //TODO 2: create one jsp page -update User
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //TODO 1:get all(6) request parameters
       //TODO 2:create an object of User Model
       //TODO 3:set all 6 request parameters values into User model -setXXX()
       //TODO 4:create an object of UserDao
       //TODO 5:Call updateUser() in UserDao
        try {

            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String birthdate = request.getParameter("birthdate");

            User user = new User();

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setGender(gender);
            user.setBirthDate(birthdate);


            UserDao dao = new UserDao();


            int n=dao.updateUser(con, user);

            if(n>0)
            response.sendRedirect("login");
        } catch (SQLException e) { e.printStackTrace(); }
        }



    }
