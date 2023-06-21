<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <%@ include file="../common/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/user/history/community.css">
    <link rel="stylesheet" href="/assets/css/board/board-write.css">
    <script src="/assets/js/board/community.js" defer type="module"></script>
</head>

<body>
    <div id="main-wrapper">
        <div class="community-wrapper">
            <div class="search-write-wrapper">
                <div class="search-container">
                    <input class="search-box" type="text">
                    <button class="board-search-title-btn">
                        <img class="searh-btn-img" src="/assets/img/community/search.png" alt="검색버튼">
                    </button>
                </div>


<div class="write-container">
<button class="modal-btn" ><p>글쓰기</p><img src="/assets/img/community/pencilwhite.png" alt="글쓰기버튼"></button>
<%@ include file="../board/board-write.jsp" %>



            <div class="sort-wrapper">
                <div class="category-wrapper">
                    <button class="freedom-board selected-category">자유</button>
                    <button class="notify-board">공지</button>
                    <button class="accuse-board">신고</button>
                </div>
                <div class="orderby-wrapper">
                    <button class="new-board-btn"><img src="/assets/img/community/sunwhite.png" alt="최신순이미지">최신순</button>
                    <button class="week-recommend-btn"><img src="/assets/img/community/firewhite.png" alt="이번주이미지">이번주 추천순</button>
                    <button class="month-recommend-btn"><img src="/assets/img/community/goodwhite.png" alt="이번달이미지">이번달 추천순</button>
                </div>
            </div>
            <div class="community-body-wrapper">
                <div class="rank-wrapper">
                    <div class="rank-head">
                        <img src="/assets/img/community/trophy.png" alt="트로피이미지">
                        <div>Best Writer</div>
                        <img src="/assets/img/community/trophy.png" alt="트로피이미지">
                    </div>
                    <div class="rank-body">
                        <ul class="rank-container">
                            <li class="rank-card">
                                <img src="/assets/img/community/firstmedal.png" alt="1등">
                                <div class="rank-nickname">예비챌린저</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/secondmedal.png" alt="2등">
                                <div class="rank-nickname">예비그랜드마스터</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/thirdmedal.png" alt="3등">
                                <div class="rank-nickname">예비마스터</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                            <li class="rank-card">
                                <img src="/assets/img/community/starmedal.png" alt="메달">
                                <div class="rank-nickname">응애롤린이</div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="board-list-wrapper">
                    <ul class="board-list-container">
                        <a href="#">
                            <li class="board-card">
                                <div class="board-recommend-box">
                                    <img src="/assets/img/community/upgray.png" alt="추천수">
                                    <div class="board-recommend">356</div>
                                </div>
                                <div class="board-body-box">
                                    <div class="board-title">안녕하세요! 신입입니다``!
                                        <span class="reply-amount">[34]</span>
                                    </div>
                                    <div class="board-info">
                                        <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                    </div>
                                </div>
                                <img class="board-img" src="/assets/img/community/naruto.jpg" alt="미리보기사진">
                                <div class="board-view-count-box">
                                    <img src="/assets/img/community/viewgray.png" alt="조회수">
                                    <div class="board-view-count">67</div>
                                </div>
                            </li>
                        </a>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/ani.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/lem.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>

                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/naruto.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/ani.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/lem.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/naruto.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/ani.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/lem.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>

                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/naruto.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/ani.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                        <li class="board-card">
                            <div class="board-recommend-box">
                                <img src="/assets/img/community/upgray.png" alt="추천수">
                                <div class="board-recommend">356</div>
                            </div>
                            <div class="board-body-box">
                                <div class="board-title">안녕하세요! 신입입니다!
                                    <span class="reply-amount">[34]</span>
                                </div>
                                <div class="board-info">
                                    <span class="board-category">자유</span>|<span class="board-writer">뽀로로2014</span>|<span class="board-write-time">3분전</span>
                                </div>
                            </div>
                            <img class="board-img" src="/assets/img/community/lem.jpg" alt="미리보기사진">
                            <div class="board-view-count-box">
                                <img src="/assets/img/community/viewgray.png" alt="조회수">
                                <div class="board-view-count">67</div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>

</html>