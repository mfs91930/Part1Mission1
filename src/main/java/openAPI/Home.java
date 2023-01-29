package openAPI;

import java.io.IOException;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Home")
public class Home extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
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
                "    <li><a href=\"Home\">홈</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"viewHistory-Servlet\">위치 히스토리 목록</a></li>\n" +
                "    <li>|</li>\n" +
                "    <li><a href=\"Get-wifiInfo\">Open API 와이파이 정보 가져오기</a></li>\n" +
                "</ul>\n" +
                "<br/>\n" +
                "<div>\n" +
                "    <form id=\"form\" action=\"ViewNear\" method=\"get\">\n" +
                "        LAT: <input name=\"latitude\" id=\"latitude\" value=0.0>\n" +
                "        , LNT: <input name=\"longitude\" id=\"longitude\" value=0.0>\n" +
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
        out.println("<tr>\n" +
                "        <td class=\"table_default\" colspan=\"18\">위치 정보를 입력한 후에 조회해 주세요.</td>\n" +
                "    </tr>");
        out.println("</table>\n" +
                "<script src=\"http://code.jquery.com/jquery-1.11.0.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"location.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }
}