package openAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.text.DecimalFormat;

public class SQLiteControl {
    public StringBuilder selectorHistory() {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder table = null;
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mfs91\\Desktop\\zero base\\제로베이스 과제\\newMission1\\Part1Mission1\\part1DB.db");

            String sql1 = "select * from mission1History order by ID DESC";
            preparedStatement = connect.prepareStatement(sql1);
            rs = preparedStatement.executeQuery();

            table = new StringBuilder();
            while (rs.next()) {
                DecimalFormat numberFormat = new DecimalFormat("###,##0.00");
                table.append("<tr>");
                table.append("<td>" + rs.getInt(1) + "</td>");
                table.append("<td>" + rs.getDouble(2) + "</td>");
                table.append("<td>" + rs.getDouble(3) + "</td>");
                table.append("<td>" + rs.getString(4) + "</td>");
                table.append("<td></td>");
                table.append("</tr>");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return table;
    }
    //근처 와이파이 보기
    public StringBuilder selector(Double latitude, Double longitude) {
        DistanceCalculator disCal = new DistanceCalculator();

        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder table = null;
        try {
            double myPosLat = latitude;
            double myPosLon = longitude;
            connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mfs91\\Desktop\\zero base\\제로베이스 과제\\newMission1\\Part1Mission1\\part1DB.db");

            String sql2 = "UPDATE mission1\n" +
                    "SET [거리(Km)] = (\n" +
                    "    6371 * acos(cos(radians( "+ myPosLat +
                    " )) * cos(radians(X좌표)) * cos(radians(Y좌표) - radians(" + myPosLon +")) + sin(radians(" +
                    myPosLat + ")) * sin(radians(X좌표))\n))";

            preparedStatement = connect.prepareStatement(sql2);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows update");

            String sql1 = "select * from mission1 order by [거리(Km)] limit 20";
            preparedStatement = connect.prepareStatement(sql1);
            rs = preparedStatement.executeQuery();

            table = new StringBuilder();
            while (rs.next()) {
                DecimalFormat numberFormat = new DecimalFormat("###,##0.00");
                table.append("<tr>");
                table.append("<td>" + numberFormat.format(Double.parseDouble(rs.getString(1))) +" km" + "</td>");
                table.append("<td>" + rs.getString(2) + "</td>");
                table.append("<td>" + rs.getString(3) + "</td>");
                table.append("<td>" + rs.getString(4) + "</td>");
                table.append("<td>" + rs.getString(5) + "</td>");
                table.append("<td>" + rs.getString(6) + "</td>");
                table.append("<td>" + rs.getString(7) + "</td>");
                table.append("<td>" + rs.getString(8) + "</td>");
                table.append("<td>" + rs.getString(9) + "</td>");
                table.append("<td>" + rs.getString(10) + "</td>");
                table.append("<td>" + rs.getString(11) + "</td>");
                table.append("<td>" + rs.getString(12) + "</td>");
                table.append("<td>" + rs.getString(13) + "</td>");
                table.append("<td>" + rs.getString(14) + "</td>");
                table.append("<td>" + rs.getString(15) + "</td>");
                table.append("<td>" + rs.getString(16) + "</td>");
                table.append("<td>" + rs.getString(17) + "</td>");
                table.append("</tr>");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return table;
    }
    //sql 개수
    public int cnt() {
        int cnt = 0;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mfs91\\Desktop\\zero base\\제로베이스 과제\\newMission1\\Part1Mission1\\part1DB.db");

            String sql = " select count(*) from mission1 ";
            preparedStatement = connect.prepareStatement(sql);


            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cnt;
    }

    //DB 저장
    public void makeDB(int jsonTotalCnt) throws IOException {
        int startNum = 1;
        int endNum = 1000;
        final int perHit = 1000;
        while (startNum <= jsonTotalCnt) {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode("63567a59786d66733736534875487a", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startNum), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endNum), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

            // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//        urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();
//            System.out.println(sb.toString());
            insert(sb.toString());
            startNum = startNum + perHit;
            endNum = endNum + perHit;
            if (endNum >= jsonTotalCnt ) {
                endNum = jsonTotalCnt;
            }
        }

        System.out.println("dbMake 완료");
    }
    //DB insert
    public void insert(String json) {
        Connection connect = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mfs91\\Desktop\\zero base\\제로베이스 과제\\newMission1\\Part1Mission1\\part1DB.db");

            JsonElement jsonTree = new JsonParser().parse(json);
            JsonObject root = jsonTree.getAsJsonObject();
            JsonObject TbPublicWifiInfo = root.getAsJsonObject("TbPublicWifiInfo");
            JsonArray row = TbPublicWifiInfo.getAsJsonArray("row");
            JsonArray jsonArr = (JsonArray) row;
            String SQL = " insert or ignore into mission1([거리(Km)], 관리번호, 자치구, 와이파이명, 도로명주소, " +
                    " 상세주소, [설치위치(층)], 설치유형, 설치기관, 서비스구분, 망종류, 설치년도, 실내외구분, " +
                    " wifi접속환경, X좌표, Y좌표, 작업일자) " +
                    " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            pstmt = connect.prepareStatement(SQL);
            if(jsonArr.size() > 0) {
                for (int i = 0; i < jsonArr.size(); i++) {
                    JsonObject firstRow = row.get(i).getAsJsonObject();

                    Double dis = 0.0; //거리
                    String no = firstRow.get("X_SWIFI_MGR_NO").getAsString(); //관리번호
                    String gu = firstRow.get("X_SWIFI_WRDOFC").getAsString();; //자치구
                    String wifiName = firstRow.get("X_SWIFI_MAIN_NM").getAsString(); //와이파이명
                    String roadAdd = firstRow.get("X_SWIFI_ADRES1").getAsString();; //도로명주소
                    String Add = firstRow.get("X_SWIFI_ADRES2").getAsString();; //상세주소
                    String locationFloor = firstRow.get("X_SWIFI_INSTL_FLOOR").getAsString();; //설치위치(층)
                    String setType = firstRow.get("X_SWIFI_INSTL_TY").getAsString();; //설치유형
                    String setIns = firstRow.get("X_SWIFI_INSTL_MBY").getAsString();; //설치기관
                    String serviceType = firstRow.get("X_SWIFI_SVC_SE").getAsString();; //서비스구분
                    String netType = firstRow.get("X_SWIFI_CMCWR").getAsString();; //망종류
                    int year = firstRow.get("X_SWIFI_CNSTC_YEAR").getAsInt();; //설치년도
                    String inOutDoor = firstRow.get("X_SWIFI_INOUT_DOOR").getAsString();; //실내외구분
                    String wifiEnv = firstRow.get("X_SWIFI_REMARS3").getAsString();; //와이파이접속환경
                    Double x = firstRow.get("LNT").getAsDouble();; //x좌표
                    Double y = firstRow.get("LAT").getAsDouble();; //y좌표
                    String date = firstRow.get("WORK_DTTM").getAsString();; //작업일자
                    pstmt.setString(1, String.valueOf(dis));
                    pstmt.setString(2, no);
                    pstmt.setString(3, gu);
                    pstmt.setString(4, wifiName);
                    pstmt.setString(5, roadAdd);
                    pstmt.setString(6, Add);
                    pstmt.setString(7, locationFloor);
                    pstmt.setString(8, setType);
                    pstmt.setString(9, setIns);
                    pstmt.setString(10, serviceType);
                    pstmt.setString(11, netType);
                    pstmt.setString(12, String.valueOf(year));
                    pstmt.setString(13, inOutDoor);
                    pstmt.setString(14, wifiEnv);
                    pstmt.setString(15, String.valueOf(x));
                    pstmt.setString(16, String.valueOf(y));
                    pstmt.setString(17, date);

                    int affected = pstmt.executeUpdate();

                    if (affected > 0) {
                        System.out.println(" 저장성공 ");
                    } else {
                        System.out.println(" 저장 실패 ");
                    }
                }
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
        System.out.println("db 열기 성공");
    }
    //api -> make -> insert
    public void api() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("63567a59786d66733736534875487a", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("2", "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//        urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        JsonElement jsonTree = new JsonParser().parse(sb.toString());
        JsonObject root = jsonTree.getAsJsonObject();
        JsonObject TbPublicWifiInfo = root.getAsJsonObject("TbPublicWifiInfo");
        int jsonTotalCnt = TbPublicWifiInfo.get("list_total_count").getAsInt();
        makeDB(jsonTotalCnt);
    }
}
