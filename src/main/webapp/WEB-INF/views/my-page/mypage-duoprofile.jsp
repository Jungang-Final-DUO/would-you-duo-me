<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- -----------------------페이지 인클루드 영역 ----------------------------------------------- -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/common.css">
    <link rel="stylesheet" href="/assets/css/mypage/mypage-duoprofile.css">
    <!--    <link rel="icon" href="/assets/img/main/simple-favicon-navy.png">-->
</head>
<body>

<div id="main-wrapper">
    <!--    마이페이지 좌측 메뉴바 영역 -->
    <div id="my-page-sidebar">
        <div id="my-page-menu-list">
            <p class="my-page-menu">내 정보 확인 / 변경</p>
            <p id = "register-duo-profile" class="my-page-menu">듀오 프로필 등록 / 수정</p>
            <p class = "my-page-menu">내가 쓴 글 / 댓글</p>
            <p id = "mypage-calendar" class="my-page-menu">출석 체크</p>
            <p class="my-page-menu">비밀번호 변경</p>
        </div>
    </div>
<!-- -----------------------페이지 인클루드 영역 ----------------------------------------------- -->

    <!--    마이페이지 우측 콘텐츠 영역 -->
    <div id="my-page-content">
        <div id="duo-profile-box">
            <!--        프로필 사진 -->
            <img id="duo-tier-image" src="/assets/img/mypage/TFT_Regalia_Challenger.png" alt="tier">
            <div id="duo-image-frame">
                <img id="duo-profile-image" src="/assets/img/mypage/user-profile.jpg" alt="profile-image">
            </div>

            <!--        포지션 선택 및 자기소개 -->
            <div id="position-comment-box">
                <!--            포지션 선택 -->
                <div id="preferred-position">
                    <p id="preferred-position-title">주포지션</p>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="">
                        <img class="position-image" src="/assets/img/mypage/ALL.png" alt="ALL">
                    </label>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="TOP">
                        <img class="position-image" src="/assets/img/mypage/TOP.png" alt="TOP"></label>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="JGL">
                        <img class="position-image" src="/assets/img/mypage/JGL.png" alt="JGL"></label>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="MID">
                        <img class="position-image" src="/assets/img/mypage/MID.png" alt="MID"></label>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="AD">
                        <img class="position-image" src="/assets/img/mypage/AD.png" alt="AD"></label>
                    <label class="position-option">
                        <input class="select-position" type="radio" name="position" value="SUP">
                        <img class="position-image" src="/assets/img/mypage/SUP.png" alt="SUP"></label>
                </div>
                <!--            자기소개 -->
                <label><textarea id="comment" name="comment" placeholder="자유롭게 자기소개를 입력해주세요" autofocus></textarea></label>
                <div id="submit-box">
                    <p id = "matching-point-title">매칭포인트</p><label id = "matching-point-label"><input id="matching-point" name = "matchingPoint"><span>POINT</span></label>
                    <div id = "register-duo">듀오 등록</div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>