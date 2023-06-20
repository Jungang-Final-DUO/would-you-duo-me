<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../common/static-head.jsp"%>
<link rel="stylesheet" href="/assets/css/admin/admin.css">

</head>
<body>
    <div id="main-wrapper">
    <%@ include file="../common/header.jsp" %>

    <div id="admin-wrapper">
        <div class="menu" id="left_menu">
            <ul class="Today_Total">
                <li>Today</li>
                <li>Total</li>
            </ul>
            <ul class="menu_box">
                <ul class="two_box">
                    <a href="${getUserListByAdmin}" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img" id="sign" src="/assets/img/admin/금일가입자수.png" alt="today_user"></li>
                            <li class="admin_category">금일 가입자</li>
                            <li class="mem_count">${count.todayJoinCount} 명</li>
                        </ul>
                    </a>
                    <a href="${getUserListByAdmin}" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img" id="member"src="/assets/img/admin/회원관리.png" alt="user_management"></li>
                            <li class="admin_category">회원관리</li>
                            <li class="mem_count">${count.totalJoinCount} 명</li>
                        </ul>
                    </a>
                </ul>
                <ul class="two_box">
                    <a href="/api/v1/accuses/{type}/{page}" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png" alt="today_accuse"></li>
                            <li class="admin_category">금일 신고</li>
                            <li class="mem_count">${count.todayAccuseCount} 회</li>
                        </ul>
                    </a>
                    <a href="/api/v1/accuses/{type}/{page}" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png" alt="accuse_management"></li>
                            <li class="admin_category">신고 관리</li>
                            <li class="mem_count">${count.totalAccuseCount}회</li>
                        </ul>
                    </a>
                </ul>
                <ul class="two_box">
                    <a href="/api/v1/boards/admin" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png" alt="today_board"></li>
                            <li class="admin_category">금일 작성 게시물</li>
                            <li class="mem_count">${count.todayBoardCount}건</li>
                        </ul>
                    </a>
                    <a href="/api/v1/boards/admin" class="one_box">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png" alt="board_management"></li>
                            <li class="admin_category">게시물 관리</li>
                            <li class="mem_count">${count.totalBoardCount}건</li>
                        </ul>
                    </a>
                </ul>



            </ul>

        </div>



        <div class="menu" id="right_menu">
            <div class="top_menu">
                <div class="search">
                    <input name="keyword" type="text">
                    <button><img src="/assets/img/admin/검색.png" alt="search"></button>
                </div>
                <ul class="admin_board">
                    <li><a href="/board/write">글쓰기</a></li>
                    <li><a href="/board/list">공지관리</a></li>
                </ul>
            </div>
            <!-- 회원관리 -->

            <div class="bottom_menu">
                <div class="menubar" id="menu_bar">
                    <div class="no">no</div>
                    <div class="nickname">닉네임</div>
                    <div class="gender">성별</div>
                    <div class="board_count">작성게시물수</div>
                    <div class="reply_count">작성댓글수</div>
                    <div class="warn_count">경고횟수</div>
                    <div class="point">포인트</div>
                    <div class="follow">팔로우수</div>
                    <div class="sign_date">가입일자</div>
                </div>
                <a href="/user/duo">
                    <div class="menubar">
                        <div class="no">${rowNum}</div>
                        <div class="nickname">${userAccount}</div>
                        <div class="gender">${gender}</div>
                        <div class="board_count">${boardCount} 건</div>
                        <div class="reply_count">${replyCount} 개</div>
                        <div class="warn_count">${reportCount} 회</div>
                        <div class="point">${point}</div>
                        <div class="follow">${followCount} 명</div>
                        <div class="sign_date">${joinDate}</div>
                    </div>
                </a>
                <a href="/user/duo">
                    <div class="menubar">
                        <div class="no">${rowNum}</div>
                        <div class="nickname">${userAccount}</div>
                        <div class="gender">${gender}</div>
                        <div class="board_count">${boardCount} 건</div>
                        <div class="reply_count">${replyCount} 개</div>
                        <div class="warn_count">${reportCount} 회</div>
                        <div class="point">${point}</div>
                        <div class="follow">${followCount} 명</div>
                        <div class="sign_date">${joinDate}</div>
                    </div>
                </a>

                <div class="preserve-space"></div>

                
                <div class="preserve-space">

                </div>
                
                <!-- 게시글관리 -->
                <div class="menubar" id="menu_bar">
                    <div class="no">no</div>
                    <div class="nickname">ID</div>
                    <div class="title">제목</div>
                    <div class="board_write_date">작성일자</div>
                    <div class="select_count">조회수</div>
                </div>
                <a href="/board/detail">
                    <div class="menubar">
                        <div class="no">${boardNo}</div>
                        <div class="nickname">${userNickname}</div>
                        <div class="title">${boardTitle}</div>
                        <div class="board_write_date">${writtenDate}</div>
                        <div class="select_count">${boardViewCount}</div>
                        <div id="delete">삭제</div>
                    </div>
                </a>
                <a href="/board/detail">
                    <div class="menubar">
                        <div class="no">${boardNo}</div>
                        <div class="nickname">${userNickname}</div>
                        <div class="title">${boardTitle}</div>
                        <div class="board_write_date">${writtenDate}</div>
                        <div class="select_count">${boardViewCount}</div>
                        <div id="delete">삭제</div>
                    </div>
                </a>

                <div class="preserve-space">

                </div>

                  <!-- 경고관리 -->
                  <div class="menubar" id="menu_bar">
                    <div class="accuse_no">no</div>
                    <div class="accuse_nickname">악성유저</div>
                    <div class="accuse_title">신고내역</div>
                    <div class="accuse_etc">신고내역(etc)</div>
                    <div class="accuse_count">신고횟수</div>
                </div>
                <a href="/user/accuse">
                    <div class="menubar">
                        <div class="accuse_no">${accuseNo}</div>
                        <div class="accuse_nickname">${userAccount}</div>
                        <div class="accuse_title">${accuseType}</div>
                        <div class="accuse_etc">${accuseETC}</div>
                        <div class="accuse_count">15</div>
                    </div>
                </a>
                <a href="/user/accuse">
                    <div class="menubar">
                        <div class="accuse_no">${accuseNo}</div>
                        <div class="accuse_nickname">${userAccount}</div>
                        <div class="accuse_title">${accuseType}</div>
                        <div class="accuse_etc">${accuseETC}</div>
                        <div class="accuse_count">15</div>
                    </div>
                </a>
                

            </div>
        </div>
</div>

<script>
    fetch('/api/v1/users/admin')
    .then(res=>res.json())
    .then(result =>{
                console.log(result)

        });
    

      






</script>
</body>
</html>