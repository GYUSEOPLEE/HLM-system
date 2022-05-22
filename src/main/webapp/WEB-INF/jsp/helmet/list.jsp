<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

        <title>공유 킥보드 헬멧 관리 시스템 - 헬멧 정보</title>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
            <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div id="form-collapse" class="collapse ts-xs-hide-collapse show">
                <div class="ts-form mb-0 d-flex flex-column flex-sm-row py-2 pl-2 pr-3">
                    <div class="form-group m-1 w-100">
                        <input type="text" class="form-control" id="keyword" name="managementNo" placeholder="헬멧 일련번호 or 모델명" />
                    </div>
                    <div class="form-group m-1 w-100">
                        <select class="custom-select" id="type" name="active">
                            <option value="X">전체</option>
                            <option value="Y">활성</option>
                            <option value="N">비활성</option>
                        </select>
                    </div>
                    <div class="form-group m-1 ml-auto">
                        <button type="button" class="btn btn-primary" id="search-btn" onclick="pageOver(1)">검색</button>
                    </div>
                </div>
            </div>
            <main id="ts-main">
                <section id="page-title" style="height: 20px">
                    <div class="container">
                        <div class="ts-title mb-0">
                            <div class="row">
                                <div class="ts-title mb-0 col-sm-10">
                                    <h1 style="font-weight : 400">헬멧 목록</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <div id="drawInfo" style="text-align: center"></div>
            </main>
        </div>


        <script>
            pageOver(1);
            function pageOver(pageNo) {
                xmlHttpRequest = new XMLHttpRequest();
                xmlHttpRequest.open("POST", "/helmets/" + pageNo, true);
                xmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                xmlHttpRequest.send('{"no" : "' + document.getElementById("keyword").value + '", "activation" : "' + document.getElementById("type").value + '"}');
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

            // document.getElementById("search-btn").addEventListener("click", pageOver(1), false);
        </script>

    <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>