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
    <link rel="stylesheet" href="/assets/css/common.css">
    <link rel="stylesheet" href="/assets/css/mypage/mypage-myinfo.css">
    <!--    <link rel="icon" href="/assets/img/main/simple-favicon-navy.png">-->
</head>
<body>
<div id = "profile-modal-background">
    <div id = "profile-modal">
        <div class = "arrow"><img id = "prev" src="/assets/img/mypage/arrow.png" alt = "prev"></div>
        <div id = "profile-details-frame">
            <img id = "profile-detail" src="/assets/img/mypage/user-profile.jpg" alt = "">
            <img id = "cancel" src="/assets/img/mypage/cancel-circle.png" alt = "cancel"></div>
        <div class = "arrow"><img id = "next" src="/assets/img/mypage/arrow.png" alt = "next"></div>
    </div>
</div>
<div id="main-wrapper">
    <!--    마이페이지 좌측 메뉴바 영역 -->
    <div id="my-page-sidebar">
        <div id="my-page-menu-list">
            <p id="my-info-modify" class="my-page-menu">내 정보 확인 / 변경</p>
            <p id="register-duo-profile" class="my-page-menu"><a href="/user/register-duo">듀오 프로필 등록 / 수정</a></p>
            <p class="my-page-menu">내가 쓴 글 / 댓글</p>
            <p id="mypage-calendar" class="my-page-menu">출석 체크</p>
            <p class="my-page-menu">비밀번호 변경</p>
        </div>
    </div>




</div>
</body>
</html>