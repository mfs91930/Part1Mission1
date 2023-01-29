package openAPI;
import java.io.IOException;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "GetWifiInfoServlet", value = "/Get-wifiInfo")
public class GetWifiInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SQLiteControl sqlM = new SQLiteControl();
        sqlM.api();
        int cnt = sqlM.cnt();
        response.setContentType("text/html;charset=UTF-8");
        String message = cnt + "개의 WIFI 정보를 정상적으로 저장하였습니다.";
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