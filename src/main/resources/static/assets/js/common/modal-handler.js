// 모든 모달 버튼에 이벤트를 등록하는 함수
import {getMessages} from "../chatting/messageRendering.js";

export function modalHandler($modalBtn) {


    $modalBtn.onclick = e => {
        e.preventDefault();
        const $target = e.target.closest('.modal-btn').nextElementSibling;

        if (!$target.hasAttribute('open')) {
            $target.showModal();
            // 해당 모달이 메세지 다이어로그라면 내부 메세지 출력
            // 소켓 함수 연결
            if($target.classList.contains('message-dialog')){
                const room = e.target.closest('.chatting-card').id;
                getMessages(room);
            }
        }
        else {
            $target.close();
        }
    }
}

export function addModalBtnEvent() {

    [...document.querySelectorAll('.modal-btn')].forEach(
        $modalBtn => modalHandler($modalBtn)
    );

}

export function addModalCloseEvent() {

    [...document.querySelectorAll('dialog')].forEach(
        $dialog => $dialog.onclick = e => {
            if (e.target.matches('dialog'))
                $dialog.close();
        }
    )

}