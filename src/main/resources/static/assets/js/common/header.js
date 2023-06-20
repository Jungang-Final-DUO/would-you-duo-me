import {addModalBtnEvent, addModalCloseEvent} from "./modal-handler.js";
import {getChattingList, makeChattingRoom, toBack} from "../chatting/chatting-modal.js";
import {toTopBtnHandler} from "./to-top-btn-handler.js";
import {connectSocket} from "../chatting/main.js";
import {messageRender} from "../chatting/messageRendering.js";

(async () => {
    // 채팅방 목록 불러오기
    const chatForm = await getChattingList();
    connectSocket(chatForm);

    //채팅 목록 클릭하면 메세지 렌더링
    messageRender();

    //채팅 생성하기
    makeChattingRoom();

    // 채팅 메세지 모달 안에서 채팅방 모달로 돌아가기
    toBack();

    // 모달 열고 닫는 이벤트
    addModalBtnEvent();
    addModalCloseEvent();

    // 위로가기 버튼 이벤트
    toTopBtnHandler();
})();
