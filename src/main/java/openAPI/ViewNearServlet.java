package openAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/ViewNear")
public class ViewNearServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Double latitude = Double.parseDouble(request.getParameter("latitude"));
        Double longitude = Double.parseDouble(request.getParameter("longitude"));
        insertHistory(latitude, longitude);
        SQLiteControl sqlM = new SQLiteControl();


        //html
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "    <title>와이파이 정보 구하기</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>와이파이 정보 구하기\n" +
                "</h1>\n" +
                "<ul>\n" +
                "    <li><a href=\"http://localhost:8080/Part1Mission1_war_exploded/index.jsp\">홈</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"viewHistory-Servlet\">위치 히스토리 목록</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"Get-wifiInfo\">Open API 와이파이 정보 가져오기</a></li>\n" +
                "</ul>\n" +
                "<br/>\n" +
                "<div>\n" +
                "    <form id=\"form\" action=\"ViewNear\" method=\"get\">\n" +
                "        LAT: <input name=\"latitude\" id=\"latitude\" value=\""+latitude+"\">\n" +
                "        , LNT: <input name=\"longitude\" id=\"longitude\" value=\""+longitude+"\">\n" +
                "        <input id=\"myLocation\" type=\"button\" value=\"내 위치 가져오기\">\n" +
                "        <input type=\"submit\" value=\"근처 WIPI 정보 보기\">\n" +
                "    </form>\n" +
                "</div>\n" +
                "<table>\n" +
                "    <th>거리(Km)</th>\n" +
                "    <th>관리번호</th>\n" +
                "    <th>자치구</th>\n" +
                "    <th>와이파이명</th>\n" +
                "    <th>도로명주소</th>\n" +
                "    <th>상세주소</th>\n" +
                "    <th>설치위치(층)</th>\n" +
                "    <th>설치유형</th>\n" +
                "    <th>설치기관</th>\n" +
                "    <th>서비스구분</th>\n" +
                "    <th>망종류</th>\n" +
                "    <th>설치년도</th>\n" +
                "    <th>실내외구분</th>\n" +
                "    <th>WIFI접속환경</th>\n" +
                "    <th>X좌표</th>\n" +
                "    <th>Y좌표</th>\n" +
                "    <th>작업일자</th>\n"
                );
        out.println(sqlM.selector(latitude, longitude));
        out.println("</table>\n" +
                "<script src=\"http://code.jquery.com/jquery-1.11.0.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"location.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }
    public void destroy() {
    }

    public void insertHistory(Double latitude, Double longitude) {
        Connection connect = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mfs91\\Desktop\\zero base\\제로베이스 과제\\newMission1\\Part1Mission1\\part1DB.db");

            String SQL = " insert into mission1History(ID, X좌표, Y좌표, 조회일자, 비고) " +
                    " values(?, ?, ?, ?, ?); ";

            pstmt = connect.prepareStatement(SQL);
//            int  id = 1; //id
            Double X = latitude; //x좌표
            Double Y = longitude; //y좌표
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatedNow = nowTime.format(formatter);
            String seDate = nowDate + "T" + formatedNow;
            String etf = "0";
//            pstmt.setInt(1, id);
            pstmt.setDouble(2, X);
            pstmt.setDouble(3, Y);
            pstmt.setString(4, seDate);
            pstmt.setString(5, etf);

            int affected = pstmt.executeUpdate();

            if (affected > 0) {
                System.out.println(" 저장성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}