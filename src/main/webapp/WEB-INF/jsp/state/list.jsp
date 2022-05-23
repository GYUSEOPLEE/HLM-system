<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

    <title>공유 킥보드 헬멧 관리 시스템 - 헬멧 상태정보</title>
</head>
<body style="font-family: 'Noto Sans KR', sans-serif;">
<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
    <br>
    <br>
    <br>
    <br>
    <br>

    <main id="ts-main">
        <section id="page-title" style="height: 20px">
            <div class="container">
                <div class="ts-title mb-0">
                    <div class="row">
                        <div class="ts-title mb-0 col-sm-10">
                            <h1 style="font-weight : 400">헬멧 상태정보 목록</h1>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="drawInfo" style="text-align: center"></div>
    </main>
    <div style="margin-right: 210px"><a href="/helmets/${helmetNo}" class="nav-link px-2 link-dark" style="font-weight: bold; font-size: 1em; float: right;background-color: blue;color: white;">헬멧 정보</a></div>

    <input type="hidden" id="helmetNo" value="${helmetNo}"/>
</div>


<script>
    let helmetNo = document.getElementById("helmetNo").value;

    pageOver(1);
    function pageOver(pageNo) {
        console.log(helmetNo);
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("POST", "/helmetstates/"+ helmetNo + "/" + pageNo, true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
        xmlHttpRequest.send('{"no" : -1, "helmetNo" : "' + helmetNo + '"}');
        console.log('{"pageNo" : "' + pageNo + '"}');
        xmlHttpRequest.onreadystatechange = getData;
    }

    function getData() {
        let drawData;
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            drawData = xmlHttpRequest.responseText;
            if (drawData != null) {
                document.getElementById("drawInfo").innerHTML = drawData;
            }
        }
    }

</script>

<%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
</body>
</html>