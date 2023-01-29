<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기
</h1>
<ul>
    <li><a href="http://localhost:8080/Part1Mission1_war_exploded/index.jsp">홈</a></li>
    <li>|</li>
    <li><a href="viewHistory-Servlet">위치 히스토리 목록</a></li>
    <li>|</li>
    <li><a href="Get-wifiInfo">Open API 와이파이 정보 가져오기</a></li>
</ul>
<br/>
<div>
    <form id="form" action="ViewNear" method="get">
        LAT: <input name="latitude" id="latitude" value="0.0">
        , LNT: <input name="longitude" id="longitude" value="0.0">
        <input id="myLocation" type="button" value="내 위치 가져오기">
        <input type="submit" value="근처 WIPI 정보 보기">
    </form>
</div>
<table>
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
    <tr>
        <td class="table_default" colspan="18">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
</table>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<script type="text/javascript" src="location.js"></script>
</body>
</html>