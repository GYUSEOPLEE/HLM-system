<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>로그인 화면</title>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/dragscroll.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.scrollbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap">
</head>
<body style="font-family: 'Noto Sans KR', sans-serif;">
<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
    <header id="ts-header" class="fixed-top">
        <nav id="ts-primary-navigation" class="navbar navbar-expand-md navbar-light">
            <div class="container" style="text-align: center">
                <p style="font-size: 2.5em; font-weight : 700; color: #0b0b0b;" >공유 킥보드 헬멧 모니터링 시스템</p>
            </div>
        </nav>
    </header>
    <br>
    <br>
    <br>
    <br>
    <main id="ts-main">
        <section id="breadcrumb" />
        <section id="login-register">
            <br><br>
            <div class="container">
                <div class="row col-md-9" style="margin: auto">
                    <div class="offset-md-2 col-md-8 offset-lg-3 col-lg-6">
                        <ul class="nav nav-tabs" id="login-register-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="login-tab" data-toggle="tab" role="tab">
                                    <h3 style="font-weight : 500">관리자 인증</h3>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" style="font-weight : 100" id="login" role="tabpanel" aria-labelledby="login-tab">
                                <form action="/login" method="post" class="ts-form" id="form-login">
                                    <div class="form-group">
                                        <input type="text"  class="form-control" id="login-email" name="id" placeholder="아이디" required="" />
                                    </div>
                                    <div class="input-group ts-has-password-toggle">
                                        <input type="password" class="form-control border-right-0" name="password" placeholder="비밀번호" required="" />
                                        <div class="input-group-append">
                                            <a href="#" class="input-group-text bg-white border-left-0 ts-password-toggle">
                                                <i class="fa fa-eye"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <div class="mr-auto p-0 custom-control custom-checkbox mb-0">
                                            <c:if test="${adminMatch eq false}">
                                                <p style="font-size: 1em" class="text-danger">아이디 또는 비밀번호가 일치 하지 않습니다.</p>
                                            </c:if>
                                        </div>
                                        <button style="font-weight : 100" type="submit" class="p-2 btn btn-primary">로그인</button>
                                    </div>
                                    <hr>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</div>
</div>
</body>
</html>