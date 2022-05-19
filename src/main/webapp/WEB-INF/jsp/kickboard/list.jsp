<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

    <title>킥보드 목록</title>
</head>
<body style="font-family: 'Noto Sans KR', sans-serif;">
<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
    <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
            <section class="ts-shadow__sm ts-z-index__2 ts-bg-light">
                <div id="form-collapse" class="collapse ts-xs-hide-collapse show">
                    <div class="ts-form mb-0 d-flex flex-column flex-sm-row py-2 pl-2 pr-3">
                        <div class="form-group m-1 w-100">
                            <select class="custom-select" id="type" name="active">
                                <option value="X">전체</option>
                                <option value="Y">활성</option>
                                <option value="N">비활성</option>
                            </select>
                        </div>
                        <div class="form-group m-1 w-100">
                            <input type="text" class="form-control" id="keyword" name="managementNo" placeholder="관리번호" />
                        </div>
                        <div class="form-group m-1 ml-auto">
                            <button type="button" class="btn btn-primary" id="search-btn">검색</button>
                        </div>
                    </div>
                </div>
            </section>

        <div id="drawInfo" style="text-align: center"></div>
<script>
    pageOver(1);

    function pageOver(pageNo) {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("POST", "/kickboards", true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");

        xmlHttpRequest.send('{"pageNo" : "' + pageNo + '"}');

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