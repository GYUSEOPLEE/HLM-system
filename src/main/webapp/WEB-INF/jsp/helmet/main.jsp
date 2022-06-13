<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
        <script src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=2fxsselz7s"></script>

        <title>공유 킥보드 헬멧 관리 시스템 - 메인</title>

        <style>
            #tableSetting {
                border-right: 1px solid #444444;
            }
        </style>
    </head>
    <body style="font-family: 'Noto Sans KR', sans-serif;">
        <section id="ts-hero" class=" mb-0">
            <div class="ts-full-screen d-flex flex-column">
                <section class="ts-shadow__sm ts-z-index__2 ts-bg-light">
                    <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
<%--                    검색 라인 감추기 버튼--%>
<%--                    <div class="position-absolute w-100 ts-bottom__0 ts-z-index__1 text-center ts-h-0">--%>
<%--                        <button type="button" class="ts-circle p-3 bg-white ts-shadow__sm border-0 ts-push-up__50 mt-2" data-toggle="collapse" data-target="#form-collapse">--%>
<%--                            <i class="fa fa-chevron-up ts-text-color-primary ts-visible-on-uncollapsed"></i>--%>
<%--                            <i class="fa fa-chevron-down ts-text-color-primary ts-visible-on-collapsed"></i>--%>
<%--                        </button>--%>
<%--                    </div>--%>

<%--                검색 라인--%>
                    <div id="form-collapse" class="collapse ts-xs-hide-collapse show">
                        <div class="ts-form mb-0 d-flex flex-column flex-sm-row py-2 pl-2 pr-3">
                            <div class="form-group m-1 w-100">
                                <input type="text" class="form-control" id="keyword" name="managementNo" placeholder="헬멧 일련번호 or 모델명" />
                            </div>
                            <div class="form-group m-1 w-100">
                                <select class="custom-select" id="type" name="activation">
                                    <option value="X">전체</option>
                                    <option value="Y">활성</option>
                                    <option value="N">비활성</option>
                                </select>
                            </div>
                            <div class="form-group m-1 ml-auto">
                                <button type="button" class="btn btn-primary" id="search-btn">검색</button>
                            </div>
                            <div class="form-group m-1 ml-auto">
                                <button type="button" class="btn btn-primary" id="reset-btn">리셋</button>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="d-flex h-100">
                    <div class="ts-results__vertical ts-results__vertical-list ts-shadow__sm scrollbar-inner bg-white">
                        <section id="ts-results">
                            <div id="drawResult"  class="ts-results-wrapper" style="overflow: auto"></div>
                        </section>
                    </div>

                    <div class="ts-map w-100">
                        <div class="ts-map w-100">
                            <div id="map" class="h-100"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <script>
        let viewHelmet = null;
        let dataToJson;
        let mapOptions = {
            center: new naver.maps.LatLng(36.797999, 127.074503),
            zoom: 15
        };
        let map = new naver.maps.Map('map', mapOptions);
        let markers = new Map();
        let element;
        let infowindows = [];
        let webSocket = new WebSocket("ws://" + location.host + "/helmets/main");

        search();
        reload();

        function setViewHelmet(no) {
            viewHelmet = no;
        }

        //검색 조건에 따라 헬멧 상태 정보 목록 가져오기
        function search() {
            searchXmlHttpRequest = new XMLHttpRequest();
            searchXmlHttpRequest.open("POST", "/helmets/main", true);
            searchXmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
            searchXmlHttpRequest.send('{"no" : "' + document.getElementById("keyword").value + '", "activation" : "' + document.getElementById("type").value + '"}');
            searchXmlHttpRequest.onreadystatechange = getData;
        }

        //가져온 목록 뿌리기
        function getData() {
            viewHelmet = null;

            if (searchXmlHttpRequest.readyState == 4 && searchXmlHttpRequest.status == 200) {
                dataToJson = JSON.parse(searchXmlHttpRequest.responseText);
            }
            if (markers.length > 0) {
                markers = new Map();
            }
            if (dataToJson != null) {
                if (dataToJson.length > 0) {
                    var script = "";
                    for (var i = 0; i < dataToJson.length; i++) {
                        let no = dataToJson[i].no;
                        let dateTime = dataToJson[i].dateTime;
                        let latitude = dataToJson[i].latitude;
                        let longitude = dataToJson[i].longitude;
                        let activation = dataToJson[i].activation;
                        let loss = dataToJson[i].loss;
                        let wear = dataToJson[i].wear;
                        let activationKr = "";
                        let color;

                        if (activation == "Y") {
                            activationKr = "활성"
                        } else {
                            activationKr = "비활성"
                            color = "text-danger";
                        }
                            script += '<div class="ts-result-link card ts-item ts-card ts-result" data-ts-id="6" data-ts-ln="5" >'
                                + '    <a href="javascript:void(0);" id="move-btn' + i + '" onclick="moveMap(\'' + no + '\', ' + latitude + ', ' + longitude + ', ' + i + ')">'
                                + '        <input type="hidden" id="no' + i + '" value="' + no + '" />'
                                + '        <input type="hidden" id="dateTime' + i + '" value="' + dateTime + '" />'
                                + '        <input type="hidden" id="latitude' + i + '" value="' + latitude + '" />'
                                + '        <input type="hidden" id="longitude' + i + '" value="' + longitude + '" />'
                                + '        <input type="hidden" id="activation' + i + '" value="' + activation + '" />'
                                + '        <input type="hidden" id="loss' + i + '" value="' + loss + '" />'
                                + '        <input type="hidden" id="wear' + i + '" value="' + wear + '" />'
                                + '        <input type="hidden" id="activationKr' + i + '" value="' + activationKr + '" />'

                                + '        <table>'
                                + '             <tr>'
                                + '                 <td id="tableSetting">' + (i+1) + '</td>'
                                + '                 <td>'
                                + '                     <div class="card-body">'
                                + '                         <figure class="ts-item__info">'
                                + '                             <h3>' + no + '</h3>'
                                + '                             <aside class="' + color + '" style="font-size: 1em">'
                                + '                                 <i class="fa fa-map-marker mr-2"></i>'
                                +                                   activationKr
                                + '                             </aside>'
                                + '                         </figure>'
                                + '                     </div>'
                                + '                 </td>'
                                + '             </tr>'
                                + '        </table>'
                                + '    </a>'
                                + '</div>';

                        let iconUrl;
                        //마커 색
                        if (loss == 'N') {
                            if (wear == 'N') {
                                iconUrl = "http://3.39.218.6/assets/img/marker-image/icon-circle-yellow.png";
                            } else {
                                iconUrl = "http://3.39.218.6/assets/img/marker-image/icon-circle-green.png";
                            }
                        } else {
                            if (wear == 'N') {
                                iconUrl = "http://3.39.218.6/assets/img/marker-image/icon-circle-red.png";
                            } else {
                                iconUrl = "http://3.39.218.6/assets/img/marker-image/icon-circle-orange.png";
                            }
                        }

                        var marker = new naver.maps.Marker({
                            position: new naver.maps.LatLng(latitude, longitude),
                            map: map,
                            icon: {
                                url: iconUrl,
                                size: new naver.maps.Size(22, 22),
                                origin: new naver.maps.Point(0, 0),
                                anchor: new naver.maps.Point(11, 35)
                            }
                        });



                        element = [i, marker]
                        markers.set(no, element);
                    }
                    document.getElementById("drawResult").innerHTML = script;
                    for (var i = 0; i < dataToJson.length; i++) {


                        //마커 하나하나 위치


                        //마커 윈도우 정보
                        var contentString = [
                            '<section style="margin: auto; width: fit-content" class="mb-1 pl-0">'
                            + '    <input type="hidden" id="contentString" value="' + i + '" />'
                            // + '    <div class="mb-2 card ts-item ts-card ts-result" data-toggle="tooltip" data-placement="right" title="상세 정보 보기">'
                            // + '        <a style="text-align: center; width: 100%; font-size: 1.5em" href="/parasol/' + document.getElementById("id" + i).value + '">' + document.getElementById("managementNo" + i).value + '</a>'
                            // + '    </div>'
                            + '    <div style="text-align: center; margin: auto" class="row">'
                            + '        <div class="col-sm-4">'
                            + '            <label>분실</label>'
                            + '            <p>' + document.getElementById("loss" + i).value + '</p>'
                            + '        </div>'
                            + '        <div class="col-sm-4">'
                            + '            <label>착용</label>'
                            + '            <p>' + document.getElementById("wear" + i).value + '</p>'
                            + '        </div>'
                            + '    </div>'
                            + '</section>'
                        ].join('');
                    }
                } else {
                    document.getElementById("drawResult").innerHTML = '<div class="ts-result-link " data-ts-id="6" data-ts-ln="5">'
                        + '    <p class="card ts-item ts-card ts-result">'
                        + '        <div class="card-body">'
                        + '            <figure class="ts-item__info">'
                        + '                <h4 class="text-center">검색 결과가 없습니다</h4>'
                        + '            </figure>'
                        + '        </div>'
                        + '    </p>'
                        + '</div>';
                }
            }
        }

        function receiveStatus(indexI) {
            StatusXmlHttpRequest = new XMLHttpRequest();
            StatusXmlHttpRequest.open("GET", "/helmetstates/" + document.getElementById("no" + indexI).value + "/info", true);
            StatusXmlHttpRequest.setRequestHeader("Content-Type","application/json;charset=UTF-8");
            StatusXmlHttpRequest.send();
            StatusXmlHttpRequest.onreadystatechange = function () {
                if (StatusXmlHttpRequest.readyState == 4 && StatusXmlHttpRequest.status == 200) {
                    parasolStatus = JSON.parse(StatusXmlHttpRequest.responseText);
                    shiftElement(parasolStatus, indexI);
                }
            }
        }

        //마커 값 변경
        function shiftElement(parasolStatus, indexI) {
            document.getElementById("no" + indexI).setAttribute('value', parasolStatus.no);
            document.getElementById("dateTime" + indexI).setAttribute('value', parasolStatus.dateTime);
            document.getElementById("latitude" + indexI).setAttribute('value', parasolStatus.latitude);
            document.getElementById("longitude" + indexI).setAttribute('value', parasolStatus.longitude);
        }

        //누른 항목으로 지도 이동
        function moveMap(no, latitude, longitude, index) {
            setViewHelmet(no);

            map.setCenter(new naver.maps.LatLng(latitude, longitude));
            map.setZoom(20);
        }

        //헬멧 정보 들어올 때마다 그리기
        function reload() {
            webSocket.onmessage = function(msg) {
                let helmetState = JSON.parse(msg.data);

                let no = helmetState.helmetNo;
                let index = markers.get(no)[0];
                let latitude = helmetState.latitude;
                let longitude = helmetState.longitude;
                let loss = helmetState.loss;
                let wear = helmetState.wear;

                markers.get(helmetState.helmetNo)[1].setMap(null);

                let iconUrl;
                //마커 색
                if (loss == 'N') {
                    if (wear == 'N') {
                        iconUrl = "http://localhost/assets/img/marker-image/icon-circle-yellow.png";
                    } else {
                        iconUrl = "http://localhost/assets/img/marker-image/icon-circle-green.png";
                    }
                } else {
                    if (wear == 'N') {
                        iconUrl = "http://localhost/assets/img/marker-image/icon-circle-red.png";
                    } else {
                        iconUrl = "http://localhost/assets/img/marker-image/icon-circle-orange.png";
                    }
                }

                var marker = new naver.maps.Marker({
                    position: new naver.maps.LatLng(latitude, longitude),
                    map: map,
                    icon: {
                        url: iconUrl,
                        size: new naver.maps.Size(22, 22),
                        origin: new naver.maps.Point(0, 0),
                        anchor: new naver.maps.Point(11, 35)
                    }
                });

                markers.get(helmetState.helmetNo)[1] = marker;

                document.getElementById("move-btn" + index).setAttribute("onClick", "moveMap('" + no + "', '" + latitude + "', '" + longitude + "', '" + index + "')")

                if (viewHelmet != null
                        && no == viewHelmet) {
                    moveMap(no, latitude, longitude, index);
                }
            }
        }

        function reset() {
            viewHelmet = null;
        }

        document.getElementById("search-btn").addEventListener("click", search, false);
        document.getElementById("reset-btn").addEventListener("click", reset, false);
    </script>

    <%@ include file="/WEB-INF/jsp/include/bottom.jsp" %>
    </body>
</html>