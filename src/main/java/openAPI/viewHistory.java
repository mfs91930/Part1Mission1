package openAPI;
import java.io.IOException;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "viewHistoryServlet", value = "/viewHistory-Servlet")
public class viewHistory extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
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
                "<h1>위치 히스토리 목록\n" +
                "</h1>\n" +
                "<ul>\n" +
                "    <li><a href=\"http://localhost:8080/Part1Mission1_war_exploded/index.jsp\">홈</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"viewHistory-Servlet\">위치 히스토리 목록</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"Get-wifiInfo\">Open API 와이파이 정보 가져오기</a></li>\n" +
                "</ul>\n" +
                "<br/>\n" +

                "<table>\n" +
                "    <th>ID</th>\n" +
                "    <th>X좌표</th>\n" +
                "    <th>Y좌표</th>\n" +
                "    <th>조회일자</th>\n" +
                "    <th>비고</th>\n"
        );
        out.println(sqlM.selectorHistory());
        out.println("</table>\n" +
                "<script src=\"http://code.jquery.com/jquery-1.11.0.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"location.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }

    public void destroy() {
    }
}