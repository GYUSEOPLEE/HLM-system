<head>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>

    <title>공공 파라소 관리 시스템 - 상태 정보</title>
</head>
<body style="font-family: 'Noto Sans KR', sans-serif;">
<div class="ts-page-wrapper ts-has-bokeh-bg" id="page-top">
    <%@ include file="/WEB-INF/jsp/include/header.jsp"%>

        <div id="drawInfo" style="text-align: center"></div>
</div>

<script>
    pageOver(1);

    function pageOver(pageNo) {
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open("POST", "/kickboards/${kickboards.no}", true);
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