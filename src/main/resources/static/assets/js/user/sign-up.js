import {addModalBtnEvent, addModalCloseEvent} from "../common/modal-handler.js";


(()=> {
    // // 인증 버튼 클릭 시 모달 창 표시
    // document.getElementById('verification-btn').addEventListener('click', function() {
    //     document.getElementById('email-confirm-modal').style.display = 'block';
    // });

// 확인 버튼 클릭 시 인증 처리 로직 구현
//     document.getElementById('confirm-btn').addEventListener('click', function() {
//         // TODO: 입력된 인증번호를 서버로 전송하고 유효성을 검사하는 로직 작성
//         // 유효한 인증번호인 경우 처리하는 코드 작성
//
//         // 모달 창 닫기
//         document.getElementById('email-confirm-modal').style.display = 'none';
//     });
//
// // 이메일 재전송 버튼 클릭 시 처리 로직 구현
//     document.getElementById('resend-btn').addEventListener('click', function() {
//         // TODO: 이메일 재전송 로직 작성
//
//         // 이메일 재전송 성공 시 알림 메시지 표시
//         alert('이메일이 재전송되었습니다.');
//     });


    document.getElementById("verification-btn").addEventListener("click", function() {
        var userEmail = document.getElementById("user-email").value;

        // AJAX 요청을 통해 서버에 이메일 주소 전송
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/send-verification-email");
        xhr.setRequestHeader("Content-Type", "application/json");

        // 이메일 주소를 JSON 형식으로 변환하여 요청 본문에 포함
        var data = JSON.stringify({ "userEmail": userEmail });

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 성공적으로 이메일을 전송한 경우에 대한 처리
                    alert("인증 이메일이 발송되었습니다.");
                } else {
                    // 이메일 전송 실패 시에 대한 처리
                    alert("인증 이메일 전송에 실패했습니다. 오류 메시지: " + xhr.responseText);
                }
            }
        };

        xhr.send(data);
    });


    addModalBtnEvent();
    addModalCloseEvent();




})();
