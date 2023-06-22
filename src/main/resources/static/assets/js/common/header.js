import {addModalBtnEvent, addModalCloseEvent} from "./modal-handler.js";
import {makeChattingRoom, toBack} from "../chatting/chatting-modal.js";
import {toTopBtnHandler} from "./to-top-btn-handler.js";
import {connectSocket} from "../chatting/main.js";
import {addDropdownEvent} from "./sign-in-handler.js";

(async () => {

    // 로그인 했을때만 실행되는 함수
    if (document.getElementById('chatting-btn')) {
        //채팅 목록 최초 랜더
        // getChattingList();

        // 소켓 연결
        connectSocket();

        //헤더 채팅 버튼 클릭하면 채팅 목록 렌더링
        // openChattingList();

        //채팅 생성하기
        makeChattingRoom();

        // 채팅 메세지 모달 안에서 채팅방 모달로 돌아가기
        toBack();

        // 내 정보 모달 드롭다운 이벤트
        addDropdownEvent('user-info-btn', 'my-page-modal-wrapper');
    } else {

        // 로그인 드롭다운 이벤트
        addDropdownEvent('sign-in-btn', 'sign-in-modal-wrapper');
    }
    // 모달 열고 닫는 이벤트
    addModalBtnEvent();
    addModalCloseEvent();

    // 위로가기 버튼 이벤트
    toTopBtnHandler();

})();
