<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="../common/static-head.jsp" %>
    <link rel="stylesheet" href="/assets/css/admin/admin.css">

    <style>
        /* 페이지 css */
        /* 페이지 액티브 기능 */
        .pagination .page-item.p-active a {
            color: #fff !important;
            pointer-events: none;
        }

        .pagination .page-item:hover a {
            color: #fff !important;
        }
    </style>
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
                        <div class="one_box" id="todayUserButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img" id="sign" src="/assets/img/admin/금일가입자수.png"
                                        alt="today_user">
                                </li>
                                <li class="admin_category">금일 가입자</li>
                                <li class="mem_count">${count.todayJoinCount} 명</li>
                            </ul>
                        </div>
                        <div class="one_box" id="totalUserButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img" id="member" src="/assets/img/admin/회원관리.png"
                                        alt="user_management"></li>
                                <li class="admin_category">회원관리</li>
                                <li class="mem_count">${count.totalJoinCount} 명</li>
                            </ul>
                        </div>
                    </ul>
                    <ul class="two_box">
                        <div class="one_box" id="todayAccuseButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png"
                                        alt="today_accuse"></li>
                                <li class="admin_category">금일 신고</li>
                                <li class="mem_count">${count.todayAccuseCount} 회</li>
                            </ul>
                        </div>
                        <div class="one_box" id="totalAccuseButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png"
                                        alt="accuse_management">
                                </li>
                                <li class="admin_category">신고 관리</li>
                                <li class="mem_count">${count.totalAccuseCount}회</li>
                            </ul>
                        </div>
                    </ul>
                    <ul class="two_box">
                        <div class="one_box" id="todayBoardButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png"
                                        alt="today_board"></li>
                                <li class="admin_category">금일 작성 게시물</li>
                                <li class="mem_count">${count.todayBoardCount}건</li>
                            </ul>
                        </div>
                        <div class="one_box" id="totalBoardButton">
                            <ul class="one_box_text">
                                <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png"
                                        alt="board_management">
                                </li>
                                <li class="admin_category">게시물 관리</li>
                                <li class="mem_count">${count.totalBoardCount}건</li>
                            </ul>
                        </div>
                    </ul>


                </ul>

            </div>


            <div class="menu" id="right_menu">
                <div class="top_menu">
                    <div class="search">
                        <input id="search_input" name="keyword" type="text">
                        <button id="search_button"><img src="/assets/img/admin/검색.png" alt="search"></button>
                    </div>
                    <ul class="admin_board">
                        <li><a href="/board/write">글쓰기</a></li>
                        <li><a href="/board/list">공지관리</a></li>
                    </ul>
                </div>
                <!-- 회원관리 -->

                <div class="bottom_menu">
                    <div class="menubar" id="user_menu_bar" style="display: none;">
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
                    <% for (int i = 1; i < 11; i++) { %>
                    <a href="javascript:void(0);"
                        onclick="window.location.href = '/user/detail/admin?userNickname=' + userNickname;"
                        class="user_li" style="display: none;">
                        <div class="menubar">
                            <div class="no uln"></div>
                            <div class="nickname uli"></div>
                            <div class="gender ulg"></div>
                            <div class="board_count ulb"></div>
                            <div class="reply_count ulr"></div>
                            <div class="warn_count ulw"></div>
                            <div class="point ulp"></div>
                            <div class="follow ulf"></div>
                            <div class="sign_date uls"></div>
                        </div>
                    </a>

                    <% } %>

                    <% for (int i = 1; i < 11; i++) { %>
                    <a href="javascript:void(0);"
                        onclick="window.location.href = '/user/detail/admin?userNickname=' + userNickname;"
                        class="user_li2" style="display: none;">
                        <div class="menubar">
                            <div class="no uln1"></div>
                            <div class="nickname uli1"></div>
                            <div class="gender ulg1"></div>
                            <div class="board_count ulb1"></div>
                            <div class="reply_count ulr1"></div>
                            <div class="warn_count ulw1"></div>
                            <div class="point ulp1"></div>
                            <div class="follow ulf1"></div>
                            <div class="sign_date uls1"></div>
                        </div>
                    </a>

                    <% } %>


                    <!-- 게시글관리 -->
                    <div class="menubar" id="board_menu_bar" style="display: none;">
                        <div class="no">no</div>
                        <div class="nickname">닉네임</div>
                        <div class="title">제목</div>
                        <div class="board_write_date">작성일자</div>
                        <div class="select_count" id="board_select_count">조회수</div>
                    </div>
                    <% for (int i = 1; i < 11; i++) { %>

                    <a href="#" class="board_li" style="display: none;">
                        <div class="menubar">
                            <div class="no bln"></div>
                            <div class="nickname blm"></div>
                            <div class="title blt"></div>
                            <div class="board_write_date bld"></div>
                            <div class="select_count blc"></div>
                            <div class="delete" onclick="getBoardNo(yourList)">삭제</div>
                        </div>
                    </a>
                    <% } %>


                    <!-- 경고관리 -->
                    <div class="menubar" id="accuse_menu_bar" style="display: none;">
                        <div class="accuse_no">no</div>
                        <div class="accuse_nickname">악성유저</div>
                        <div class="accuse_title">신고내역</div>
                        <div class="accuse_etc">신고내역(etc)</div>
                        <div class="accuse_count">신고날짜</div>
                    </div>

                    <% for (int i = 1; i < 11; i++) { %>

                    <a href="#" class="accuse_li" style="display: none;">
                        <div class="menubar">
                            <div class="accuse_no aln"></div>
                            <div class="accuse_nickname alm"></div>
                            <div class="accuse_title alt"></div>
                            <div class="accuse_etc ale"></div>
                            <div class="accuse_count alc"></div>
                        </div>
                    </a>
                    <% } %>
                    <ul class="pageNo">
                        <ul class="pagination justify-content-center page_no">
                        </ul>
                    </ul>


                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp" %>
    </div>


    <script>
        // 클릭 이벤트 핸들러 등록 - 전체 회원
        const totalUserButton = document.getElementById('totalUserButton');
        const todayUserButton = document.getElementById('todayUserButton');
        const UserMenuBar = document.getElementById('user_menu_bar');
        const userList = document.getElementsByClassName('user_li');
        const userTodayList = document.getElementsByClassName('user_li2');


        // 클릭 이벤트 핸들러 등록 - 전체 게시글
        const totalBoardButton = document.getElementById('totalBoardButton');
        const todayBoardButton = document.getElementById('todayBoardButton');
        const boardMenuBar = document.getElementById('board_menu_bar');
        const boardList = document.getElementsByClassName('board_li');


        // 클릭 이벤트 핸들러 등록 - 전체 경고리스트
        const totalAccuseButton = document.getElementById('totalAccuseButton');
        const todayAccuseButton = document.getElementById('todayAccuseButton');
        const accuseMenuBar = document.getElementById('accuse_menu_bar');
        const accuseList = document.getElementsByClassName('accuse_li');


        function boardDisplayNone() {
            boardMenuBar.style.display = 'none';
            for (let i = 0; i < boardList.length; i++) {
                boardList[i].style.display = 'none';
            }
        }

        function accuseDisplayNone() {
            accuseMenuBar.style.display = 'none';
            for (let i = 0; i < accuseList.length; i++) {
                accuseList[i].style.display = 'none';
            }
        }


        function userDisplayNone() {
            UserMenuBar.style.display = 'none';
            for (let i = 0; i < userList.length; i++) {
                userList[i].style.display = 'none';
            }
        }


        totalUserButton.onclick = e => {
            console.log('today userlist click 실행');

            boardDisplayNone();
            accuseDisplayNone();

            UserMenuBar.style.display = '';

            const pageNum = 1; // 초기 페이지 설정

            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');

            let keyword = ""; // 초기에는 빈 문자열로 설정합니다.
            console.log('클릭전 키워드', keyword);

            button.onclick = e => {
                keyword = searchInput.value; // 입력된 텍스트 값으로 keyword를 업데이트합니다.
                console.log(keyword);

                if (keyword == "") {
                    keyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getUserList(pageNum, keyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 keyword가 빈 문자열로 호출됩니다.)
            getUserList(pageNum, keyword);
            console.log('함수전달할 때  키워드', keyword);
        };

        function getUserList(pageNum, keyword) {
            fetch(`/api/v1/users/admin?pageNum=\${pageNum}&keyword=\${keyword}`)
                .then(response => response.json())
                .then(res => {
                    const list = res.list;

                    for (let i = 0; i < userList.length; i++) {
                        const userLink = userList[i];

                        if (i < list.length) {
                            const {
                                rowNum,
                                userNickname,
                                gender,
                                boardCount,
                                replyCount,
                                reportCount,
                                point,
                                followCount,
                                joinDate,
                            } = list[i];

                            const userRowNumElement = userLink.querySelector('.uln');
                            userRowNumElement.innerText = rowNum;

                            const userNicknameElement = userLink.querySelector('.uli');
                            userNicknameElement.innerText = userNickname;

                            const userGenderElement = userLink.querySelector('.ulg');
                            userGenderElement.innerText = gender;

                            const userBoardElement = userLink.querySelector('.ulb');
                            userBoardElement.innerText = boardCount;

                            const userReplyElement = userLink.querySelector('.ulr');
                            userReplyElement.innerText = replyCount;

                            const userReportElement = userLink.querySelector('.ulw');
                            userReportElement.innerText = reportCount;

                            const userPointElement = userLink.querySelector('.ulp');
                            userPointElement.innerText = point;

                            const userFollowElement = userLink.querySelector('.ulf');
                            userFollowElement.innerText = followCount;

                            const userJoinElement = userLink.querySelector('.uls');
                            userJoinElement.innerText = joinDate;

                            userLink.style.display = '';
                        } else {
                            userLink.style.display = 'none';
                        }
                    }
                    console.log('마지막res', res);
                    console.log('마지막list', list);

                    renderUserList(res);
                    locationToDetail(list);
                })

        }

        function renderUserList({
            count,
            pageInfo,
            list
        }) {
            console.log('userList count: ', count);
            console.log('userList pageInfo: ', pageInfo);
            console.log('userList list: ', list);

            // 페이지 렌더링
            renderPage(pageInfo);
        }

        //페이징
        function renderPage({
            startPage,
            endPage,
            currentPage,
            prev,
            next,
            totalCount,
            PAGE_COUNT
        }) {
            console.log(`userList 페이지 렌더링 함수`);

            let tag = "";

            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = startPage; i <= endPage; i++) {
                let active = '';
                if (currentPage.pageNo === i) {
                    console.log(currentPage.pageNo);
                    active = 'p-active';
                }

                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
                    "'>다음</a></li>";
            }

            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;

            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = endPage;
        }

        // 유저페이지 클릭 이벤트 핸들러
        function makePageButtonUserClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                console.log("======total", pageNum);

                // 페이지 번호에 맞는 목록 비동기 요청
                getUserList(pageNum);

                const $pageUl = document.querySelector('.pagination');

            };
        }

        makePageButtonUserClickEvent();

        //클릭할때


        function locationToDetail(list) {
            console.log('list: ', list);

            for (let i = 0; i < list.length; i++) {
                const userNickname = list[i].userNickname; // userAccount 값을 가져옴

                userList[i].onclick = e => {
                    console.log('userNickname: ', userNickname);

                    window.location.href = '/user/detail/admin?userNickname=' + userNickname;
                };
            }
        }

        // 금일 유저

        todayUserButton.onclick = e => {
            console.log('today userlist click 실행');

            boardDisplayNone();
            accuseDisplayNone();

            UserMenuBar.style.display = '';

            const pageNum = 1; // 초기 페이지 설정

            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');

            let keyword = ""; // 초기에는 빈 문자열로 설정합니다.
            console.log('클릭전 키워드', keyword);

            button.onclick = e => {
                keyword = searchInput.value; // 입력된 텍스트 값으로 keyword를 업데이트합니다.
                console.log(keyword);

                if (keyword == "") {
                    keyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getUserTodayList(pageNum, keyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 keyword가 빈 문자열로 호출됩니다.)
            getUserTodayList(pageNum, keyword);
            console.log('함수전달할 때  키워드', keyword);
        };


        function getUserTodayList(pageNum, keyword) {
            fetch(`/api/v1/users/admin1?pageNum=\${pageNum}&keyword=\${keyword}`)
                .then(response => response.json())
                .then(res => {
                    const list = res.list;
                    console.log('list', list);

                    for (let i = 0; i < userList.length; i++) {
                        const userLink = userList[i];

                        if (i < list.length) {
                            const {
                                rowNum,
                                userNickname,
                                gender,
                                boardCount,
                                replyCount,
                                reportCount,
                                point,
                                followCount,
                                joinDate,
                            } = list[i];



                            const userRowNumElement = userLink.querySelector('.uln');
                            userRowNumElement.innerText = rowNum;

                            const userNicknameElement = userLink.querySelector('.uli');
                            userNicknameElement.innerText = userNickname;

                            const userGenderElement = userLink.querySelector('.ulg');
                            userGenderElement.innerText = gender;

                            const userBoardElement = userLink.querySelector('.ulb');
                            userBoardElement.innerText = boardCount;

                            const userReplyElement = userLink.querySelector('.ulr');
                            userReplyElement.innerText = replyCount;

                            const userReportElement = userLink.querySelector('.ulw');
                            userReportElement.innerText = reportCount;

                            const userPointElement = userLink.querySelector('.ulp');
                            userPointElement.innerText = point;
                            const userFollowElement = userLink.querySelector('.ulf');
                            userFollowElement.innerText = followCount;

                            const userJoinElement = userLink.querySelector('.uls');
                            userJoinElement.innerText = joinDate;
                            userLink.style.display = '';
                        } else {
                            userLink.style.display = 'none';
                        }
                    }


                    renderUserTodayList(res);
                    console.log('왜안찍혀');

                    console.log('list: ', list);

                    locationTodayToDetail(list);

                });
        }


        function renderUserTodayList({
            count,
            pageInfo,
            list
        }) {
            console.log('count: ', count);
            console.log('pageInfo: ', pageInfo);
            console.log('list: ', list);

            //페이지 렌더링
            renderUserTodayPage(pageInfo);
        }

        //페이징
        function renderUserTodayPage({
    startPage,
    endPage,
    currentPage,
    prev,
    next,
    totalCount,
    PAGE_COUNT
}) {
    console.log(`userList 페이지 렌더링 함수`);

    let tag = "";

    //이전 버튼 만들기
    if (prev) {
        tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
            "'>이전</a></li>";
    }
    //페이지 번호 리스트 만들기
    for (let i = startPage; i <= endPage; i++) {
        let active = '';
        if (currentPage.pageNo === i) {
            console.log(currentPage.pageNo);
            active = 'p-active';
        }

        tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
            "'>" + i + "</a></li>";
    }
    //다음 버튼 만들기
    if (next) {
        tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
            "'>다음</a></li>";
    }

    // 페이지태그 렌더링
    const $pageUl = document.querySelector('.pagination');
    $pageUl.innerHTML = tag;

    // ul에 마지막페이지 번호 저장.
    $pageUl.dataset.fp = endPage;
}

// 수정된 코드
function renderUserTodayList({
    count,
    pageInfo,
    list
}) {
    console.log('userList count: ', count);
    console.log('userList pageInfo: ', pageInfo);
    console.log('userList list: ', list);

    // 페이지 렌더링
    renderUserTodayPage(pageInfo);
}

        // 유저페이지 클릭 이벤트 핸들러
        function makePageButtonUserTodayClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                console.log("======today", pageNum)

                // 페이지 번호에 맞는 목록 비동기 요청
                getUserTodayList(pageNum);
            };
        }


        // 페이지 버튼 이벤트 등록
        makePageButtonUserTodayClickEvent();

        function locationTodayToDetail(list) {
            console.log('list: ', list);

            for (let i = 0; i < list.length; i++) {
                const userNickname = list[i].userNickname; // userAccount 값을 가져옴

                userList[i].onclick = e => {
                    console.log('userNickname: ', userNickname);

                    window.location.href = '/user/detail/admin?userNickname=' + userNickname;
                };
            }
        }

        //보드


        totalBoardButton.onclick = e => {
            userDisplayNone();
            accuseDisplayNone();
            boardMenuBar.style.display = '';

            const pageNum = 1; // 초기 페이지 설정

            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');

            let keyword = ""; // 초기에는 빈 문자열로 설정합니다.

            button.onclick = e => {
                keyword = searchInput.value; // 입력된 텍스트 값으로 keyword를 업데이트합니다.
                console.log(keyword);

                if (keyword == "") {
                    keyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getBoardList(pageNum, keyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 keyword가 빈 문자열로 호출됩니다.)
            getBoardList(pageNum, keyword);
            console.log('함수전달할 때  키워드', keyword);
        };


        function getBoardList(pageNum, keyword) {

            fetch(`/api/v1/boards/admin?pageNum=\${pageNum}&keyword=\${keyword}`)
                .then(response => response.json())
                .then(res => {
                    const list = res.list;

                    for (let i = 0; i < boardList.length; i++) {
                        const boardLink = boardList[i];

                        if (i < list.length) {

                            const {
                                boardNo,
                                userAccount,
                                boardTitle,
                                boardWrittenDate,
                                boardViewCount
                            } = list[i];

                            const boardNoElement = boardLink.querySelector('.bln');
                            boardNoElement.innerText = boardNo;

                            const userAccountElement = boardLink.querySelector('.blm');
                            userAccountElement.innerText = userAccount;

                            const boardTitleElement = boardLink.querySelector('.blt');
                            boardTitleElement.innerText = boardTitle;

                            const boardWrittenDateElement = boardLink.querySelector('.bld');
                            boardWrittenDateElement.innerText = boardWrittenDate;

                            const boardViewCountElement = boardLink.querySelector('.blc');
                            boardViewCountElement.innerText = boardViewCount;

                            boardLink.style.display = '';
                        } else {
                            boardLink.style.display = 'none';
                        }
                    }

                    const yourList = list; // yourList에 list를 할당

                    const boardDelete = document.querySelectorAll('.delete');
                    for (let index = 0; index < boardDelete.length; index++) {
                        boardDelete[index].onclick = function () {
                            if (confirm('게시물을 삭제하시겠습니까?')) {
                                alert('게시물 삭제 완료');
                                deleteBoard(yourList, index);
                            }
                        };
                    }

                    renderBoardList(res);
                });
        }



        //게시물삭제
        function deleteBoard(list, index) {
            const boardNo = list[index].boardNo;
            console.log('asd123====', boardNo);

            fetch(`/api/v1/boards/admin/delete?boardNo=` + boardNo, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(res => {
                    console.log('res:', res);

                    // 게시물 삭제 후 새로운 데이터 가져오기
                    fetch('/api/v1/boards/admin') // 새로운 데이터 가져오기 API 엔드포인트로 변경해야 함
                        .then(response => response.json())
                        .then(res => {
                            const newList = res.list;
                            // 새로운 데이터를 이용하여 화면 업데이트
                            updateBoardList(newList);
                        });
                });
        }

        function updateBoardList(newList) {
            // 게시물 리스트 업데이트
            bln(newList);
            blm(newList);
            blt(newList);
            bld(newList);
            blc(newList);
        }


        //보드 페이징

        function renderBoardList({
            count,
            pageInfo,
            list
        }) {
            console.log('count: ', count);
            console.log('pageInfo: ', pageInfo);
            console.log('list: ', list);

            //페이지 렌더링
            renderBoardPage(pageInfo);
        }

        //페이징
        function renderBoardPage({
            startPage,
            endPage,
            currentPage,
            prev,
            next,
            totalCount,
            PAGE_COUNT
        }) {


            let tag = "";

            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = startPage; i <= endPage; i++) {
                let active = '';
                if (currentPage.pageNo === i) {
                    console.log(currentPage.pageNo);
                    active = 'p-active';
                }

                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
                    "'>다음</a></li>";
            }

            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;

            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = finalPage;

        }

        // 유저페이지 클릭 이벤트 핸들러
        function makePageButtonBoardClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                console.log("======today", pageNum)

                // 페이지 번호에 맞는 목록 비동기 요청
                getBoardList(pageNum);
            };
        }


        // 페이지 버튼 이벤트 등록
        makePageButtonBoardClickEvent();


        todayBoardButton.onclick = e => {
            userDisplayNone();
            accuseDisplayNone();
            boardMenuBar.style.display = '';

            const pageNum = 1; // 초기 페이지 설정

            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');

            let keyword = ""; // 초기에는 빈 문자열로 설정합니다.

            button.onclick = e => {
                keyword = searchInput.value; // 입력된 텍스트 값으로 keyword를 업데이트합니다.
                console.log(keyword);

                if (keyword == "") {
                    keyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getBoardTodayList(pageNum, keyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 keyword가 빈 문자열로 호출됩니다.)
            getBoardTodayList(pageNum, keyword);
            console.log('함수전달할 때  키워드', keyword);
        };


        function getBoardTodayList(pageNum, keyword) {


            fetch(`/api/v1/boards/admin1?pageNum=\${pageNum}&keyword=\${keyword}`)
                .then(response => response.json())
                .then(res => {
                    const list = res.list;

                    for (let i = 0; i < boardList.length; i++) {
                        const boardLink = boardList[i];

                        if (i < list.length) {

                            const {
                                boardNo,
                                userAccount,
                                boardTitle,
                                boardWrittenDate,
                                boardViewCount
                            } = list[i];

                            const boardNoElement = boardLink.querySelector('.bln');
                            boardNoElement.innerText = boardNo;

                            const userAccountElement = boardLink.querySelector('.blm');
                            userAccountElement.innerText = userAccount;

                            const boardTitleElement = boardLink.querySelector('.blt');
                            boardTitleElement.innerText = boardTitle;

                            const boardWrittenDateElement = boardLink.querySelector('.bld');
                            boardWrittenDateElement.innerText = boardWrittenDate;

                            const boardViewCountElement = boardLink.querySelector('.blc');
                            boardViewCountElement.innerText = boardViewCount;

                            boardLink.style.display = '';
                        } else {
                            boardLink.style.display = 'none';
                        }
                    }

                    const yourList = list; // yourList에 list를 할당

                    const boardDelete = document.querySelectorAll('.delete');
                    for (let index = 0; index < boardDelete.length; index++) {
                        boardDelete[index].onclick = function () {
                            if (confirm('게시물을 삭제하시겠습니까?')) {
                                alert('게시물 삭제 완료');
                                deleteBoard(yourList, index);
                            }
                        };
                    }

                    renderBoardTodayList(res);
                });
        }


        function deleteTodayBoard(list, index) {
            const boardNo = list[index].boardNo;
            console.log('asd123====', boardNo);

            fetch(`/api/v1/boards/admin/delete?boardNo=` + boardNo, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(res => {
                    console.log('res:', res);

                    // 게시물 삭제 후 새로운 데이터 가져오기
                    fetch('/api/v1/boards/admin1') // 새로운 데이터 가져오기 API 엔드포인트로 변경해야 함
                        .then(response => response.json())
                        .then(res => {
                            const newList = res.list;
                            // 새로운 데이터를 이용하여 화면 업데이트
                            updateBoardList(newList);
                        });
                });
        }

        function updateBoardList(newList) {
            // 게시물 리스트 업데이트
            bln1(newList);
            blm1(newList);
            blt1(newList);
            bld1(newList);
            blc1(newList);
        }

        //보드 페이징

        function renderBoardTodayList({
            count,
            pageInfo,
            list
        }) {
            console.log('count: ', count);
            console.log('pageInfo: ', pageInfo);
            console.log('list: ', list);

            //페이지 렌더링
            renderBoardTodayPage(pageInfo);
        }

        //페이징
        function renderBoardTodayPage({
            startPage,
            endPage,
            currentPage,
            prev,
            next,
            totalCount,
            PAGE_COUNT
        }) {


            let tag = "";

            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = startPage; i <= endPage; i++) {
                let active = '';
                if (currentPage.pageNo === i) {
                    console.log(currentPage.pageNo);
                    active = 'p-active';
                }

                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
                    "'>다음</a></li>";
            }

            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;

            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = finalPage;

        }

        // 유저페이지 클릭 이벤트 핸들러
        function makePageButtonTodayBoardClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                console.log("======today", pageNum)

                // 페이지 번호에 맞는 목록 비동기 요청
                getBoardTodayList(pageNum);
            };
        }


        // 페이지 버튼 이벤트 등록
        makePageButtonTodayBoardClickEvent();


        // const keyword = '';
        // const page = 1;
        // const size = 10;
        //경고

        totalAccuseButton.onclick = e => {
            console.log('accusebutton');

            accuseMenuBar.style.display = '';

            userDisplayNone();
            boardDisplayNone();
            const pageNum = 1; // 초기 페이지 설정
            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');
            let totalKeyword = ""; // 새로운 변수 totalKeyword를 선언
            button.onclick = e => {
                totalKeyword = searchInput.value; // 입력된 텍스트 값으로 totalKeyword를 업데이트합니다.
                console.log(totalKeyword);

                if (totalKeyword == "") {
                    totalKeyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getAccusesList(pageNum, totalKeyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 totalKeyword가 빈 문자열로 호출됩니다.)
            getAccusesList(pageNum, totalKeyword);
        };

        function getAccusesList(pageNum, totalKeyword) {
            console.log('함수전달할 때  키워드', totalKeyword);

            fetch(`/api/v1/user/accuse?pageNum=\${pageNum}&keyword=\${totalKeyword}`)
                .then(response => response.json())
                .then(res => {
                    console.log('res: ', res);

                    const list = res.list;
                    console.log('list: ', list);

                    for (let i = 0; i < accuseList.length; i++) {
                        const accuseLink = accuseList[i];

                        if (i < list.length) {
                            const {
                                accuseNo,
                                userNickname,
                                accuseType,
                                accuseETC,
                                accuseWrittenDate
                            } = list[i];

                            const accuseNoElement = accuseLink.querySelector('.aln');
                            accuseNoElement.innerText = accuseNo;

                            const accuseNicknameElement = accuseLink.querySelector('.alm');
                            accuseNicknameElement.innerText = userNickname;

                            const accuseTitleElement = accuseLink.querySelector('.alt');
                            accuseTitleElement.innerText = accuseType;

                            const accuseETCElement = accuseLink.querySelector('.ale');
                            accuseETCElement.innerText = accuseETC;

                            const accuseCountElement = accuseLink.querySelector('.alc');
                            accuseCountElement.innerText = accuseWrittenDate;

                            accuseLink.style.display = '';
                        } else {
                            accuseLink.style.display = 'none';
                        }
                    }

                    renderAccuseList(res);
                });
        }


        function renderAccuseList({
            count,
            pageInfo,
            list
        }) {
            console.log('count: ', count);
            console.log('pageInfo: ', pageInfo);
            console.log('list: ', list);

            //페이지 렌더링
            renderAccuseList(pageInfo);
        }

        //페이징
        function renderAccuseList({
            startPage,
            endPage,
            currentPage,
            prev,
            next,
            totalCount,
            PAGE_COUNT
        }) {


            let tag = "";

            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = startPage; i <= endPage; i++) {
                let active = '';
                if (currentPage.pageNo === i) {
                    console.log(currentPage.pageNo);
                    active = 'p-active';
                }

                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
                    "'>다음</a></li>";
            }

            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;

            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = finalPage;

        }

        // 경고페이지 클릭 이벤트 핸들러
        function makePageButtonAccuseClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                console.log("======today", pageNum)

                // 페이지 번호에 맞는 목록 비동기 요청
                getAccusesList(pageNum);
            };
        }


        // 페이지 버튼 이벤트 등록
        makePageButtonAccuseClickEvent();



        //금일 신고

        todayAccuseButton.onclick = e => {
            accuseMenuBar.style.display = '';
            userDisplayNone();
            boardDisplayNone();

            const pageNum = 1; // 초기 페이지 설정
            const searchInput = document.getElementById('search_input');
            const button = document.getElementById('search_button');
            let keyword = "";
            button.onclick = e => {
                keyword = searchInput.value; // 입력된 텍스트 값으로 keyword를 업데이트합니다.
                console.log(keyword);

                if (keyword == "") {
                    keyword = "";
                }

                // 사용자 목록을 가져오는 함수 호출
                getAccusesTodayList(pageNum, keyword);
            };

            // 사용자 목록을 가져오는 함수 호출 (초기에는 keyword가 빈 문자열로 호출됩니다.)
            getAccusesTodayList(pageNum, keyword);
        };

        function getAccusesTodayList(pageNum, keyword) {
            fetch(`/api/v1/user/accuse1?pageNum=\${pageNum}&keyword=\${keyword}`)
                .then(response => response.json())
                .then(res => {
                    console.log('res: ', res);

                    const list = res.list;
                    console.log('list: ', list);

                    for (let i = 0; i < accuseList.length; i++) {
                        const accuseLink = accuseList[i];

                        if (i < list.length) {
                            const {
                                accuseNo,
                                userNickname,
                                accuseType,
                                accuseETC,
                                accuseWrittenDate
                            } = list[i];

                            const accuseNoElement = accuseLink.querySelector('.aln');
                            accuseNoElement.innerText = accuseNo;

                            const accuseNicknameElement = accuseLink.querySelector('.alm');
                            accuseNicknameElement.innerText = userNickname;

                            const accuseTitleElement = accuseLink.querySelector('.alt');
                            accuseTitleElement.innerText = accuseType;

                            const accuseETCElement = accuseLink.querySelector('.ale');
                            accuseETCElement.innerText = accuseETC;

                            const accuseCountElement = accuseLink.querySelector('.alc');
                            accuseCountElement.innerText = accuseWrittenDate;

                            accuseLink.style.display = '';
                        } else {
                            accuseLink.style.display = 'none';
                        }
                    }

                    aln1(list);
                    alm1(list);
                    alt1(list);
                    ale1(list);
                    alc1(list);
                    renderAccuseTodayList(res);
                });
        }

        function renderAccuseTodayList({
            count,
            pageInfo,
            list
        }) {
            console.log('count: ', count);
            console.log('pageInfo: ', pageInfo);
            console.log('list: ', list);

            //페이지 렌더링
            renderAccuseTodayList(pageInfo);
        }

        //페이징
        function renderAccuseTodayList({
            startPage,
            endPage,
            currentPage,
            prev,
            next,
            totalCount,
            PAGE_COUNT
        }) {


            let tag = "";

            //이전 버튼 만들기
            if (prev) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (startPage - 1) +
                    "'>이전</a></li>";
            }
            //페이지 번호 리스트 만들기
            for (let i = startPage; i <= endPage; i++) {
                let active = '';
                if (currentPage.pageNo === i) {
                    console.log(currentPage.pageNo);
                    active = 'p-active';
                }

                tag += "<li class='page-item " + active + "'><a class='page-link page-custom' href='" + i +
                    "'>" + i + "</a></li>";
            }
            //다음 버튼 만들기
            if (next) {
                tag += "<li class='page-item'><a class='page-link page-active' href='" + (endPage + 1) +
                    "'>다음</a></li>";
            }

            // 페이지태그 렌더링
            const $pageUl = document.querySelector('.pagination');
            $pageUl.innerHTML = tag;

            // ul에 마지막페이지 번호 저장.
            $pageUl.dataset.fp = finalPage;

        }

        // 경고페이지 클릭 이벤트 핸들러
        function makePageButtonAccuseTodayClickEvent() {
            // 페이지 버튼 클릭이벤트 처리
            const $pageUl = document.querySelector('.pagination');
            $pageUl.onclick = e => {
                if (!e.target.matches('.page-item a')) return;

                e.preventDefault(); // 태그의 기본 동작 중단

                // 누른 페이지 번호 가져오기
                const pageNum = e.target.getAttribute('href');
                // console.log(pageNum);
                console.log("======today", pageNum)

                // 페이지 번호에 맞는 목록 비동기 요청
                getAccusesTodayList(pageNum);
            };
        }


        // 페이지 버튼 이벤트 등록
        makePageButtonAccuseTodayClickEvent();
    </script>
</body>

</html>