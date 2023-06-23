import {renderUnreadMessages} from "./chatting-modal.js";
import {matchingRequestEvent, matchingResponseEvent} from "./matching.js";

export function scrollDown() {
    const chatMessages = document.querySelectorAll('.chatting-message-body');
    //scroll down
    chatMessages.forEach(cm => {
        cm.scrollTop = cm.scrollHeight;
    });
}

//메세지박스 렌더링
export function outputMessage(message) {
    console.log('outputMessage 진입');
    const userNickname = document.getElementById('loginUserInfo').dataset.userNickname;
    matchingRequestEvent();
    matchingResponseEvent();

    const room = document.getElementById(message.room);
    const otherProfile = room.querySelector('.chatting-profile-img').src;
    const matchingBtn = room.querySelector('.matching-accept-btn');
    const chatting_message_option = room.querySelector('.chatting-message-option');

    const div = document.createElement('div');
    div.classList.add('chatting-message-card');

    // 내가 보낸 메세지
    if (message.username === userNickname) {

        div.classList.add('chatting-message-card');
        div.classList.add('message-from');
        div.innerHTML = `
                <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                <div class="message-content-container">
                    <div class="message-nickname">${message.username}</div>
                    <div class="message-content-wrapper">
                        <div class="message-content">${message.text}</div>
                        <span class="send-time">${message.time}</span>
                    </div>
                </div>
            `;

    // 내가 받은 메세지일때
    } else {

        // 매칭 ststus가 request로 변하면 채팅받은 사람 버튼 변경
        if(message.matchingStatus === 'REQUEST'){
            matchingBtn.childNodes[1].nodeValue = `매칭 수락`;
            matchingBtn.disabled = false;
            matchingBtn.dataset.matchingNo = message.matchingNo;
            const gameover_container = document.createElement('div');
            gameover_container.classList.add('gameover-container');
            chatting_message_option.appendChild(gameover_container);

            const matching_reject_btn = document.createElement('button');
            matching_reject_btn.classList.add('matching-reject-btn');
            gameover_container.appendChild(matching_reject_btn);
            matching_reject_btn.append(`매칭 거절`);
            matching_reject_btn.dataset.matchingNo = message.matchingNo;
        }

        div.classList.add('chatting-message-card');
        div.classList.add('message-to');
        div.innerHTML = `
                <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                <div class="message-content-container">
                        <div class="message-nickname">${message.username}</div>
                        <div class="message-content-wrapper">
                            <div class="message-content">${message.text}</div>
                            <span class="send-time">${message.time}</span>
                        </div>
                    </div>
                    `;
    }
    room.querySelector('.chatting-message-body').appendChild(div);
}

//DB에서 메세지 읽어오기
export function getMessages(room) {

    const userId = document.getElementById('loginUserInfo').dataset.userAccount;

    fetch(`/api/v1/chat/messages/${userId}/${room}`)
        .then(res => res.json())
        .then(result => {
            setChattingDetailBox(room, result);
        });
}

//채팅방 최초 진입시 렌더링
function setChattingDetailBox(chattingNo, result) {
    // console.log('setChattingDetailBox 도달');
    const room = document.getElementById(chattingNo);
    room.querySelector('.chatting-message-body').innerHTML = '';
    const {userNickname, myProfileImage, yourProfileImage, messageList} = result;

    for (const msg of messageList) {
        const {messageFrom, messageContent, messageTime} = msg;
        const message = {
            room: chattingNo,
            username: messageFrom,
            text: messageContent,
            time: messageTime
        }
        outputMessage(message);
    }
    scrollDown();
    renderUnreadMessages(chattingNo);
}

// 메세지 저장
export function saveMessage({username, room, msg, matchingStatus, matchingNo}) {
    const messageDTO = {
        chattingNo: room,
        messageContent: msg,
        messageFrom: username
    }
    const requestInfo = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(messageDTO)
    };
    fetch(`/api/v1/chat/messages`, requestInfo)
        .then(res => res.json())
        .then(flag => {
            if (flag) console.log('메세지 저장 성공');
            else console.log('메세지 저장 실패');
        })

}

// 채팅 중일때 메세지 실시간 렌더 및 DB 저장
export function renderAndSaveMessage(message) {
    outputMessage(message);
    saveMessage(message);
}