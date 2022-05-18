<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
    <title>킥보드 </title>
</head>
<body style="font-family: 'Noto Sans KR', sans-serif;">
<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
    <br>
    <br>
    <br>
    <br>
    <main id="ts-main">
        <section id="agent-info">
            <div class="container col-md-9">
                <div class="ts-box col-md-auto">
                    <div class="row col-md-auto">
                        <div class="col-md-12">
                            <div class="py-2 col-md-12">
                                <section id="basic-information" class="mb-0 pl-3">
                                    <div class="row">
                                        <div class="ts-title mb-2 col-sm-10">
                                            <label class="badge badge-light" style="font-size: 3em; font-weight: 500;">킥보드 조회</label>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label class="badge badge-light" style="font-size: 1.5em; font-weight: 500; float: left;margin-right: 20%; margin-left: 20%;">일련번호</label>
                                            <p style="font-size: 1.5em;">${kickboard.no}</p>
                                        </div>
                                        <br>
                                        <div class="col-sm-4">
                                            <label class="badge badge-light" style="font-size: 1.5em; font-weight: 500; float: left;margin-right: 20%; margin-left: 20%;">모델명</label>
                                            <p style="font-size: 1.5em;">${kickboard.model}</p>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="badge badge-light" style="font-size: 1.5em; font-weight: 500; float: left; margin-right: 20%; margin-left: 20%;">킥보드 IP</label>
                                            <p style="font-size: 1.5em;">${kickboard.ip}</p>
                                        </div>
                                        <div class="col-sm-4">
                                            <label class="badge badge-light" style="font-size: 1.5em; font-weight: 500; float: left; margin-right: 20%; margin-left: 20%;">활성</label>
                                            <c:choose>
                                            <c:when test="${kickboard.activation == 'Y'}">
                                            <h3 class="p-2" style="margin: auto" data-toggle="tooltip" data-placement="right" title="상태 변경 : 비활성 버튼">
                                                <button type="button" class="card ts-item ts-card ts-result border text-primary" style="font-size: 1.5em" data-toggle="modal" data-target="#activeCenter">${kickboard.activation}</button>
                                                </c:when>
                                                <c:otherwise>
                                                <h3 class="p-2" style="margin: auto" data-toggle="tooltip" data-placement="right" title="상태 변경 : 활성 버튼">
                                                    <button type="button" class="card ts-item ts-card ts-result border text-danger" style="font-size: 1.5em" data-toggle="modal" data-target="#activeCenter">${kickboard.activation}</button>
                                                </c:otherwise>
                                                    </c:choose>
                                            </h3>
                                        </div>
                                        <div class="modal fade" id="checkCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="checkLongTitle">공백은 입력할 수 없습니다.</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" id="checkButton" class="btn btn-primary" data-dismiss="modal">확인</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal fade" id="activeCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="activeLongTitle">활성화 변경</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <c:choose>
                                                            <c:when test="${kickboard.activation == 'Y'}">
                                                                <h3 class="form-check">비활성 상태로 변경하시겠습니까?</h3>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <h3 class="form-check">활성 상태로 변경하시겠습니까?</h3>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                                                        <button type="button" id="editActiveButton" class="btn btn-primary">확인</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>

<script>
    document.getElementById("editActiveButton").addEventListener("click", editActive, false);

    function editActive() {
        if ("${kickboard.activation}" == "Y") {
            sendEdit({"no":"${kickboard.no}","activation":"N"});
        } else {
            sendEdit({"no":"${kickboard.no}","activation":"Y"});
        }
    }

    function sendEdit(params) {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/kickboards/${kickboard.no}");
        for(var key in params) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
            form.appendChild(hiddenField);
        }
        document.body.appendChild(form);
        form.submit();
    }
</script>

<%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
</body>
</html>