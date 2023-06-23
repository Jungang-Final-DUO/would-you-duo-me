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
                    <div class="one_box" id="todayUserButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img" id="sign" src="/assets/img/admin/금일가입자수.png" alt="today_user"></li>
                            <li class="admin_category">금일 가입자</li>
                            <li class="mem_count">${count.todayJoinCount} 명</li>
                        </ul>
                    </div>
                    <div class="one_box" id="totalUserButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img" id="member"src="/assets/img/admin/회원관리.png" alt="user_management"></li>
                            <li class="admin_category">회원관리</li>
                            <li class="mem_count">${count.totalJoinCount} 명</li>
                        </ul>
                    </div>
                </ul>
                <ul class="two_box">
                    <div class="one_box" id="todayAccuseButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png" alt="today_accuse"></li>
                            <li class="admin_category">금일 신고</li>
                            <li class="mem_count">${count.todayAccuseCount} 회</li>
                        </ul>
                    </div>
                    <div class="one_box" id="totalAccuseButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/신고사진.png" alt="accuse_management"></li>
                            <li class="admin_category">신고 관리</li>
                            <li class="mem_count">${count.totalAccuseCount}회</li>
                        </ul>
                    </div>
                </ul>
                <ul class="two_box">
                    <div class="one_box" id="todayBoardButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png" alt="today_board"></li>
                            <li class="admin_category">금일 작성 게시물</li>
                            <li class="mem_count">${count.todayBoardCount}건</li>
                        </ul>
                    </div>
                    <div class="one_box" id="totalBoardButton">
                        <ul class="one_box_text">
                            <li class="pic"><img class="admin_img accuse" src="/assets/img/admin/게시판관리.png" alt="board_management"></li>
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
                    <a href="#" class="user_li" style="display: none;">
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

 
                <!-- 게시글관리 -->
                <div class="menubar" id="board_menu_bar" style="display: none;">
                    <div class="no">no</div>
                    <div class="nickname">ID</div>
                    <div class="title">제목</div>
                    <div class="board_write_date">작성일자</div>
                    <div class="select_count">조회수</div>
                </div>
                <% for (int i = 1; i < 11; i++) { %>

                <a href="/board/detail" class="board_li"style="display: none;">
                    <div class="menubar">
                        <div class="no bln"></div>
                        <div class="nickname blm"></div>
                        <div class="title blt"></div>
                        <div class="board_write_date bld"></div>
                        <div class="select_count blc"></div>
                        <div id="delete">삭제</div>
                    </div>
                </a>
                <% } %>


                  <!-- 경고관리 -->
                  <div class="menubar" id="accuse_menu_bar" style="display: none;">
                    <div class="accuse_no">no</div>
                    <div class="accuse_nickname">악성유저</div>
                    <div class="accuse_title">신고내역</div>
                    <div class="accuse_etc">신고내역(etc)</div>
                    <div class="accuse_count">신고횟수</div>
                </div>

                <% for (int i = 1; i < 11; i++) { %>

                <a href="/user/accuse" class=accuse_li style="display: none;">
                    <div class="menubar">
                        <div class="accuse_no aln"></div>
                        <div class="accuse_nickname alm"></div>
                        <div class="accuse_title alt"></div>
                        <div class="accuse_etc ale"></div>
                        <div class="accuse_count alc"></div>
                    </div>
                </a>
                <% } %>

 
                

            </div>
        </div>
</div>

<script>




// 클릭 이벤트 핸들러 등록 - 전체 회원
const totalUserButton = document.getElementById('totalUserButton');
const UserMenuBar = document.getElementById('user_menu_bar');
const totalUserList = document.getElementsByClassName('user_li');

const totalUserButtonClickHandler = () => {
  UserMenuBar.style.display = '';
  for (let i = 0; i < totalUserList.length; i++) {
    totalUserList[i].style.display = '';
  }
};

totalUserButton.addEventListener('click', totalUserButtonClickHandler);

// 클릭 이벤트 핸들러 등록 - 전체 게시글
const totalBoardButton = document.getElementById('totalBoardButton');
const boardMenuBar = document.getElementById('board_menu_bar');
const totalBoardList = document.getElementsByClassName('board_li');

const totalBoardButtonClickHandler = () => {
  boardMenuBar.style.display = '';
  for (let i = 0; i < totalBoardList.length; i++) {
    totalBoardList[i].style.display = '';
  }
};

totalBoardButton.addEventListener('click', totalBoardButtonClickHandler);

// 이벤트 핸들러 함수 제거
totalUserButton.removeEventListener('click', totalBoardButtonClickHandler);
totalBoardButton.removeEventListener('click', totalUserButtonClickHandler);



// 클릭 이벤트 핸들러 등록 - 전체 경고리스트
const totalAccuseButton = document.getElementById('totalAccuseButton');
const accuseMenuBar = document.getElementById('accuse_menu_bar');
const totalAccuseList = document.getElementsByClassName('accuse_li');

const totalAccuseButtonClickHandler = () => {
  accuseMenuBar.style.display = '';
  for (let i = 0; i < totalAccuseList.length; i++) {
    totalAccuseList[i].style.display = '';
  }
};

totalAccuseButton.addEventListener('click', totalAccuseButtonClickHandler);











//유저

fetch('/api/v1/users/admin')
  .then(response => response.json())
  .then(res => {
    const list = res.list;
    // console.log('list: ', list);

    for (let listOne of list) {
      const {
        rowNum,
        userAccount,
        gender,
        boardCount,
        replyCount,
        reportCount,
        point,
        followCount,
        joinDate
      } = listOne;
      
    //   console.log('userAccount: ', userAccount);
      

    }
//유저리스트
    uln(list);
    uli(list);
    ulg(list);
    ulb(list);
    ulr(list);
    ulw(list);
    ulp(list);
    ulf(list);
    uls(list);

  });



function uln(list) {
  const ulArray = document.querySelectorAll('.uln');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].rowNum;
    ulElement.innerText = asd;
  });
}

function uli(list) {
  const ulArray = document.querySelectorAll('.uli');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].userAccount;
    ulElement.innerText = asd;
  });
}
function ulg(list) {
  const ulArray = document.querySelectorAll('.ulg');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].gender;
    ulElement.innerText = asd;
  });
}
function ulb(list) {
  const ulArray = document.querySelectorAll('.ulb');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].boardCount;
    ulElement.innerText = asd +"  회";
  });
}
function ulr(list) {
  const ulArray = document.querySelectorAll('.ulr');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].replyCount;
    ulElement.innerText = asd +"  회";
  });
}
function ulw(list) {
  const ulArray = document.querySelectorAll('.ulw');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].reportCount;
    ulElement.innerText = asd +"  회";
  });
}
function ulp(list) {
  const ulArray = document.querySelectorAll('.ulp');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].point;
    ulElement.innerText = asd +"  point";
  });
}
function ulf(list) {
  const ulArray = document.querySelectorAll('.ulf');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].followCount;
    ulElement.innerText = asd +"  명";
  });
}
function uls(list) {
  const ulArray = document.querySelectorAll('.uls');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].joinDate;
    ulElement.innerText = asd;
  });
}


//보드

fetch('/api/v1/boards/admin')
  .then(response => response.json())
  .then(res => {
    // console.log('res: ', res);

    const list = res.list;
    // console.log('list: ', list);

    for (let listOne of list) {
      const {
        boardNo,
        userNickName,
        boardTitle,
        boardWrittenDate,
        boardViewCount
      } = listOne;
      
    //   console.log('userAccount: ', userAccount);
      

    }
//보드리스트
    bln(list);
    blm(list);
    blt(list);
    bld(list);
    blc(list);
   
  });

  function bln(list) {
  const ulArray = document.querySelectorAll('.bln');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].boardNo;
    ulElement.innerText = asd;
  });
}

function blm(list) {
  const ulArray = document.querySelectorAll('.blm');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].userNickName;
    ulElement.innerText = asd;
  });
}

function blt(list) {
  const ulArray = document.querySelectorAll('.blt');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].boardTitle;
    ulElement.innerText = asd;
  });
}

function bld(list) {
  const ulArray = document.querySelectorAll('.bld');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].boardWrittenDate;
    ulElement.innerText = asd;
  });
}

function blc(list) {
  const ulArray = document.querySelectorAll('.blc');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].boardViewCount;
    ulElement.innerText = asd;
  });
}

// const keyword = '';
// const page = 1;
// const size = 10;
//경고
fetch('/api/v1/user/accuse')
.then(response => response.json())
.then(res => {
    console.log('res: ', res);

    const list = res.list;
    console.log('list: ', list);

    for (let listOne of list) {
      const {
        accuseNo,
        userAccount,
        accuseType,
        accuseETC,
        accuseWrittenDate
      } = listOne;
      
      

  }
//경고리스트
    aln(list);
    alm(list);
    alt(list);
    ale(list);
    alc(list);
   
  });

  function aln(list) {
  const ulArray = document.querySelectorAll('.aln');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].accuseNo;
    ulElement.innerText = asd;
  });
}

function alm(list) {
  const ulArray = document.querySelectorAll('.alm');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].userNickName;
    ulElement.innerText = asd;
  });
}

function alt(list) {
  const ulArray = document.querySelectorAll('.alt');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].accuseType;
    ulElement.innerText = asd;
  });
}

function ale(list) {
  const ulArray = document.querySelectorAll('.ale');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].accuseETC;
    ulElement.innerText = asd;
  });
}

function alc(list) {
  const ulArray = document.querySelectorAll('.alc');
  ulArray.forEach((ulElement, index) => {
    const asd = list[index].accuseWrittenDate;
    ulElement.innerText = asd;
  });
}

    

</script>
</body>
</html>