<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="../common/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/my-page/matching-list.css">
    <!--    <link rel="icon" href="/assets/img/main/simple-favicon-navy.png">-->
</head>
<body>

<div id="main-wrapper">

    <%@ include file="../common/header.jsp" %>
    <div id="my-page-wrapper">

        <!--    마이페이지 좌측 메뉴바 영역 -->
        <%@ include file="my-page.jsp" %>

        <!-- 이 아래에 div 태그 열어서 width: 80% 으로 주기 -->
        <!--    마이페이지 우측 콘텐츠 영역 -->
        <div id="my-page-content">
            <!--        원하는 내용 추가 -->
            <div class="matching-box">

                <div class="application-match">
                    <h2>내가 신청한 매칭</h2>

                    <ul class="duo-record">
                        <li class="duo-header">
                            <p class="matching-peolpe">매칭상대</p>

                            <p class="matching-day">매칭일</p>
                            <div class="my-review">
                                내가작성한후기
                            </div>

                        </li>
                    </ul>
                </div>

                <!-- 내가 수락한 매칭 -->
                <div class="application-mymatch">
                    <h2>내가 수락한 매칭</h2>

                    <ul class="duo-record">
                        <li class="duo-header">
                            <p class="matching-peolpe">매칭상대</p>

                            <p class="matching-day">매칭일</p>
                            <div class="my-review">
                                내가작성한후기
                            </div>

                        </li>

                    </ul>

                </div>


            </div>
        </div>
    </div>
</div>
</body>
</html>