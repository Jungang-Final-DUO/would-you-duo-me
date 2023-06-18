import {addModalBtnEvent, addModalCloseEvent} from "../common/modal-handler.js";


(() => {

    addModalBtnEvent();
    addModalCloseEvent();
    addConfirmBtnHandler();

    document.getElementById("verification-btn").addEventListener("click", function () {
        var userEmail = document.getElementById("user-email").value;

        // AJAX 요청을 통해 서버에 이메일 주소 전송
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/user/send-email");
        xhr.setRequestHeader("Content-Type", "application/json");

        // 이메일 주소를 JSON 형식으로 변환하여 요청 본문에 포함
        var data = JSON.stringify({"userEmail": userEmail});

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // 성공적으로 이메일을 전송한 경우에 대한 처리
                    var authCode = xhr.responseText;
                    showConfirmationModal(authCode); // 모달 창 열기 및 인증 코드 표시
                } else {
                    // 이메일 전송 실패 시에 대한 처리
                    alert("인증 이메일 전송에 실패했습니다. 오류 메시지: " + xhr.responseText);
                }
            }
        };

        xhr.send(data);
    });

// 모달 창 열기 및 인증 코드 표시
    function showConfirmationModal(authCode) {
        var modal = document.querySelector("dialog");
        var confirmCodeInput = modal.querySelector("#confirm-code");
        confirmCodeInput.placeholder = "남은 시간 03:00";
        confirmCodeInput.value = authCode;
        modal.showModal();
    }

    // 인증 버튼 핸들러
    function addConfirmBtnHandler() {
        document.getElementById('email-confirm-modal')
            .querySelector('button').onclick = async e => {
            e.preventDefault();
            const res = await fetch("/user/check-email",
                {
                    method: 'POST',
                    body: document.getElementById('confirm-code').value
                });

            if (res.status === 499) {
                alert('인증번호가 맞지 않습니다!');
            } else if (res.status === 200) {
                alert('인증되었습니다!');
            }

        }
    }
})();
