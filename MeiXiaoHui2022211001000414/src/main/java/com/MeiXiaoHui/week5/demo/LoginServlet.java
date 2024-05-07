package com.MeiXiaoHui.week5.demo;

import com.MeiXiaoHui.dao.UserDao;
import com.MeiXiaoHui.model.User;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Connection con=null;

    @Override
    public void init() {
//        String driver = getServletContext().getInitParameter("driver");
//        String url = getServletContext().getInitParameter("url");
//        String username = getServletContext().getInitParameter("username");
//        String password = getServletContext().getInitParameter("password");
//
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        con=(Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//    doPost(request,response);
    request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username1 = request.getParameter("username");
//        String password1 = request.getParameter("password");
//        String sql = "select * from usertable where username=? and password=?";
//        try (PreparedStatement pstmt =con.prepareStatement(sql)){
//            pstmt.setString(1,username1);
//            pstmt.setString(2,password1);
//            ResultSet rs=pstmt.executeQuery();
//            PrintWriter writer = response.getWriter();
//            ResultSet rs = con.createStatement().executeQuery(sql);
//            if(rs.next()) {
//                //week5 code
//                String Username = rs.getString("username");
//                String Password = rs.getString("password");
//                if(username1.equals(Username) && password1.equals(Password)){
//                    writer.println("Login Success!!!");
//                    writer.println("Welcome,"+username1);
//                }
//            } else {
//                writer.println("Username or Password Error!!!"); }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserDao userDao=new UserDao();
        try {
            User user=userDao.findByUsernamePassword(con,username,password);
            if(user!=null){
                // cookie

                //add code for remember me
                String rememberMe=request.getParameter("rememberMe");//1 =checked
                if(rememberMe!=null && rememberMe.equals("1")){
                    Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie=new Cookie("cRememberMe",rememberMe);

                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);

                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                //create a session
               HttpSession session= request.getSession();//session get
                System.out.println("session id-->"+session.getId());//session id
                session.setMaxInactiveInterval(10*60);// 10 sec is too less
//                Cookie c=new Cookie("sessionid",""+user.getId());
//                c.setMaxAge(10*60);
//                response.addCookie(c);

                //request.setAttribute("user",user);
                session.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/useInfo.jsp").forward(request,response);
            }else{
                request.setAttribute("massage","Username or Password Error!!!" );
                request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

/*
        String sql = "SELECT id, username, password, email, gender, birthdate FROM usertable WHERE username = '" + username + "' AND password = '" + password + "'";
        try {
            ResultSet rs=con.createStatement().executeQuery(sql);
            if(rs.next()){

                request.setAttribute("id",rs.getString("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthdate",rs.getString("birthdate"));

                request.getRequestDispatcher("/week2/useInfo.jsp").forward(request,response);
            }
            else {
                request.setAttribute("massage","Username or Password Error!!!" );
                request.getRequestDispatcher("/week2/Login.jsp").forward(request,response);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        */
        }
    }




