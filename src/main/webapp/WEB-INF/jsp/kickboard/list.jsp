<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<form action="/kickboards" method="post">
    <h1>킥보드 목록</h1>
    <select name="activation">
        <option value="">활성화 선택</option>
        <option value="Y">활성</option>
        <option value="N">비활성</option>
    </select>
    <input type="text" id="search">
    <input type="submit" value="검색">
</form>
<table border="1px">
    <tr>
        <th>번호</th>
        <th>일련번호</th>
        <th>킥보드 IP</th>
        <th>모델</th>
        <th>활성</th>
    </tr>
    <c:forEach items="${kickboards}" var="item" varStatus="status">
        <tr>
            <th>${status.count}</th>
            <th>${item.no}</th>
            <th>${item.ip}</th>
            <th>${item.model}</th>
            <th>${item.activation}</th>
        </tr>
    </c:forEach>
</table>

</body>
</html>