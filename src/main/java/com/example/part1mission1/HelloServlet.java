package com.example.part1mission1;

import java.io.IOException;

import openAPI.SQLiteControl;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String message = "개의 WIFI 정보를 정상적으로 저장하였습니다.";
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<style>body {text-align: center}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='page2'><h1>" + message + "</h1>");
        out.println("<a href = 'http://localhost:8080/Part1Mission1_war_exploded/'>홈 으로 가기</a></div>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}