<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="../common/static-head.jsp"%>
    <link rel="stylesheet" href="/assets/css/admin/admin_user.css">
    <link rel="stylesheet" href="/assets/css/admin/adminModal.css">


</head>

<body>
    <%@ include file="../common/header.jsp" %>

    <div id="main-wrapper">

        <div class="side_container">
            <div class="direction_img"><img src="/assets/img/admin/왼쪽방향.png" alt="left"></div>
        </div>





        <div class="center_container">
            <div id="admin_page"><a href="/api/v1/users/admin">관리자페이지</a></div>
            <div id="main_controller">

                <c:if test="${udByAdmin.banned}">
                    <div id="is_ben" name="userIsBanned" style="background-color: red;">BAN</div>
                </c:if>
                <div id="is_ben">BAN</div>
                <div id="userinfo">
                    <div id="user_pic_container">
                        <button class="profile cursor">
                            <img src="/assets/img/admin/우기main.png" alt="first_img">
                            <img src="/assets/img/admin/사진교체금지.png" alt="first_hover_img" class="hover-img"
                                id="first_hover_img">
                        </button>
                        <div class="bottom_profile">
                            <button class="profile2 cursor">
                                <img src="/assets/img/admin/우기2.png" alt="second_img">
                                <img src="/assets/img/admin/사진교체금지.png" alt="second_hover_img" class="hover-img">
                            </button>
                            <button class="profile2 cursor">
                                <img src="/assets/img/admin/우기2.png" alt="third_img">
                                <img src="/assets/img/admin/사진교체금지.png" alt="third_hover_img" class="hover-img">
                            </button>
                        </div>
                    </div>
                    <div id="userinfo_content">
                        <div id="user_name">${udByAdmin.userNickname}</div>
                        <div id="detail_info">
                            <ul id="left_info">
                                <ul class="first_info_ul">
                                    <li class="first_info_li">가입일자</li>
                                    <li class="second_info_li">최근접속일자</li>
                                    <li class="third_info_li">작성게시글수</li>
                                    <li class="forth_info_li">작성댓글수</li>
                                    <li class="fifth_info_li">팔로워수</li>
                                </ul>
                                <ul class="second_info_ul">
                                    <li class="first_info_li">${udByAdmin.joinDate}</li>
                                    <li class="second_info_li">${udByAdmin.recentLoginDate}</li>
                                    <li class="third_info_li">${udByAdmin.boardCount} 개</li>
                                    <li class="forth_info_li">${udByAdmin.replyCount} 개</li>
                                    <li class="fifth_info_li">${udByAdmin.followCount} 명</li>
                                </ul>
                            </ul>
                            <ul id="right_info">
                                <ul class="first_info_ul">
                                    <li class="first_info_li">현재 포인트</li>
                                    <li class="second_info_li">지급 포인트</li>
                                    <li class="third_info_li">경고</li>
                                </ul>
                                <ul class="second_info_ul">
                                    <li class="first_info_li" id="userCurrentPoint" name="userCurrentPoint">
                                        ${udByAdmin.point}</li>
                                    <div id="div_contain">
                                        <li class="second_info_li" id="secound_info_li"><input type="text"
                                                name="userAddPoint" id="give_point" onchange='addPoint()'></li>
                                        <button class="payment" id="payment">지급</button>
                                    </div>
                                    <div id="img_li">
                                        <!-- 모달 -->
                                        <div>
                                            <a class="accuse_click modal-btn"><img class="accuse_img"
                                                    src="/assets/img/admin/경고W.png" alt="warn"></a>
                                            <dialog class="container">
                                                <div id="main-wrapper">
                                                    <form action="/user/accuse" method="POST" class="container">
                                                        <div class="inbox">
                                                            <ul class="inbox_text">
                                                                <li>플레이어 신고하기</li>
                                                            </ul>
                                                            <div class="inbox_img"><img src="/assets/img/admin/비행기.png" alt="send"></div>
                                                        </div>
                                            
                                                        <ul class="checkbox_table">
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 타인을 비방 / 모방하는 글 또는 댓글 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 사진 및 게시물 게시</label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse" >
                                                                <label for="check_accuse"> 타 유저 경고 확인 결과 관리자 판단하의 추가 경고 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 프로필 사진 업로드 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 기타 : </label>
                                                                <input type="text" id="acc"> 
                                                            </div>
                                                        </ul>
                                            
                                            
                                                    </form>
                                            
                                            
                                                </div>
                                            </dialog>
                                        </div>
                                        <div>
                                            <a class="accuse_click modal-btn"><img class="accuse_img"
                                                    src="/assets/img/admin/경고W.png" alt="warn"></a>
                                            <dialog class="container">
                                                <div id="main-wrapper">
                                                    <form action="/user/accuse" method="POST" class="container">
                                                        <div class="inbox">
                                                            <ul class="inbox_text">
                                                                <li>플레이어 신고하기</li>
                                                            </ul>
                                                            <div class="inbox_img"><img src="/assets/img/admin/비행기.png" alt="send"></div>
                                                        </div>
                                            
                                                        <ul class="checkbox_table">
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 타인을 비방 / 모방하는 글 또는 댓글 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 사진 및 게시물 게시</label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse" >
                                                                <label for="check_accuse"> 타 유저 경고 확인 결과 관리자 판단하의 추가 경고 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 프로필 사진 업로드 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 기타 : </label>
                                                                <input type="text" id="acc"> 
                                                            </div>
                                                        </ul>
                                            
                                            
                                                    </form>
                                            
                                            
                                                </div>
                                            </dialog>
                                        </div>
                                        <div>
                                            <a class="accuse_click modal-btn"><img class="accuse_img"
                                                    src="/assets/img/admin/경고W.png" alt="warn"></a>
                                            <dialog class="container">
                                                <div id="main-wrapper">
                                                    <form action="/user/accuse" method="POST" class="container">
                                                        <div class="inbox">
                                                            <ul class="inbox_text">
                                                                <li>플레이어 신고하기</li>
                                                            </ul>
                                                            <div class="inbox_img"><img src="/assets/img/admin/비행기.png" alt="send"></div>
                                                        </div>
                                            
                                                        <ul class="checkbox_table">
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 타인을 비방 / 모방하는 글 또는 댓글 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 사진 및 게시물 게시</label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse" >
                                                                <label for="check_accuse"> 타 유저 경고 확인 결과 관리자 판단하의 추가 경고 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 프로필 사진 업로드 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 기타 : </label>
                                                                <input type="text" id="acc"> 
                                                            </div>
                                                        </ul>
                                            
                                            
                                                    </form>
                                            
                                            
                                                </div>
                                            </dialog>
                                        </div>
                                        <div>
                                            <a class="accuse_click modal-btn"><img class="accuse_img"
                                                    src="/assets/img/admin/경고W.png" alt="warn"></a>
                                            <dialog class="container">
                                                <div id="main-wrapper">
                                                    <form action="/user/accuse" method="POST" class="container">
                                                        <div class="inbox">
                                                            <ul class="inbox_text">
                                                                <li>플레이어 신고하기</li>
                                                            </ul>
                                                            <div class="inbox_img"><img src="/assets/img/admin/비행기.png" alt="send"></div>
                                                        </div>
                                            
                                                        <ul class="checkbox_table">
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 타인을 비방 / 모방하는 글 또는 댓글 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 사진 및 게시물 게시</label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse" >
                                                                <label for="check_accuse"> 타 유저 경고 확인 결과 관리자 판단하의 추가 경고 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 프로필 사진 업로드 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 기타 : </label>
                                                                <input type="text" id="acc"> 
                                                            </div>
                                                        </ul>
                                            
                                            
                                                    </form>
                                            
                                            
                                                </div>
                                            </dialog>
                                        </div>
                                        <div>
                                            <a class="accuse_click modal-btn"><img class="accuse_img"
                                                    src="/assets/img/admin/경고W.png" alt="warn"></a>
                                            <dialog class="container">
                                                <div id="main-wrapper">
                                                    <form action="/user/accuse" method="POST" class="container">
                                                        <div class="inbox">
                                                            <ul class="inbox_text">
                                                                <li>플레이어 신고하기</li>
                                                            </ul>
                                                            <div class="inbox_img"><img src="/assets/img/admin/비행기.png" alt="send"></div>
                                                        </div>
                                            
                                                        <ul class="checkbox_table">
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 타인을 비방 / 모방하는 글 또는 댓글 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 사진 및 게시물 게시</label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse" >
                                                                <label for="check_accuse"> 타 유저 경고 확인 결과 관리자 판단하의 추가 경고 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 부적절한 프로필 사진 업로드 </label>
                                                            </div>
                                                            <div class="checkbox_content">
                                                                <input type="checkbox" name="accuseType" class="check_input" id="check_accuse">
                                                                <label for="check_accuse"> 기타 : </label>
                                                                <input type="text" id="acc"> 
                                                            </div>
                                                        </ul>
                                            
                                            
                                                    </form>
                                            
                                            
                                                </div>
                                            </dialog>
                                        </div>
                                    </div>
                                </ul>
                            </ul>
                            <!-- <button class="payment" id="payment">지급</button> -->

                        </div>

                    </div>

                </div>

            </div>


        </div>


        <div class="side_container">
            <div class="direction_img"><img src="/assets/img/admin/오른쪽방향.png" alt="left"></div>

        </div>
    </div>


    <script>
        //경고 클릭시 빨간색
        const accuseImages = document.querySelectorAll('.accuse_click img');

        accuseImages.forEach(image => {
            image.addEventListener('click', function () {
                const newImageSrc = '/assets/img/admin/경고R.png';
                this.setAttribute('src', newImageSrc);


            });
        });


        // 현재 포인트
        const userCurrentPoint = document.getElementById('userCurrentPoint');

        //추가포인트
        const pay = document.getElementById('give_point');
        //현재포인트 int 변환
        const point = parseInt(userCurrentPoint.innerText);
        // const add = parseInt(AddPoint.value);

        //추가지급 입력포인트 int변환
        function addPoint() {
            const givePointInput = document.getElementById('give_point');
            add = parseInt(givePointInput.value);

        }



        // 포인트 지급 버튼
        const payment = document.getElementById('payment');
        // 해당 유저 닉네임
        const user = document.getElementById('user_name');





        //Ban 클릭 변수
        const banClick = document.getElementById('is_ben');

        banClick.onclick = e => {
            const userNickname = user.innerText;
            console.log(userNickname);

            fetch(`http://localhost:8282/user/ban`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        userIsBanned: 1,
                        userNickname: user.innerText
                    })
                })
                .then(response => {
                    return response.json()
                })
                .then(res => {
                    console.log('res  :  -' + res);


                    if (res === true) {
                        banClick.style.backgroundColor = 'red';
                    } else {
                        banClick.style.backgroundColor = '#111E30';
                    }
                    console.log(res);
                });

        };


        




        pay.value = 0;
        // 포인트 버튼 클릭 시 현재 포인트에 추가 포인트 더하기
        payment.onclick = e => {

            const total = point + add;


            //포인트 정보 전송
            fetch('http://localhost:8282/user/point', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        userAddPoint: add,
                        userNickname: user.innerText
                    })
                })
                .then(response => {
                    return response.json()
                })
                .then(res => {
                    console.log('res  :  -' + res);

                    document.getElementById('userCurrentPoint').innerText = res;
                    pay.value = 0;

                })
        };
    </script>
</body>

</html>