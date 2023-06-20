import {renderUnreadMessages} from "./chatting-modal.js";

export function messageRender(){
    [...document.querySelectorAll('.chatting-card')].forEach(
        cc => {
            cc.addEventListener('click', getMessages);
        }
    )
}

export function outputMessage(message) {
    const room = document.getElementById(message.room);
    const otherProfile = room.querySelector('.chatting-profile-img').src;

    const div = document.createElement('div');
    div.classList.add('chatting-message-card');

    if (message.username === 'test1') {
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
    } else {
        div.classList.add('chatting-message-card');
        div.classList.add('message-to');
        div.innerHTML = `
                <img class="chatting-profile" src="${otherProfile}" alt="프로필이미지">
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
export function getMessages(e){
    const chattingNo = e.target.closest('.chatting-card').id;
    console.log(chattingNo);
    const userId = 'test1';

    fetch(`/api/v1/chat/messages/${userId}/${chattingNo}`)
        .then(res => res.json())
        .then(result => {
            setChattingDetailBox(chattingNo, result);
        })
}

//채팅방 최초 진입시 렌더링
function setChattingDetailBox(chattingNo, result){
    const {userNickname, myProfileImage, yourProfileImage, messageList} = result;

    for (const msg of messageList) {
        const {messageFrom, messageContent, messageTime} = msg;
        const message = {
            room : chattingNo,
            username : messageFrom,
            text : messageContent,
            time : messageTime
        }
        outputMessage(message);
    }
    renderUnreadMessages(chattingNo);

}

// 메세지 저장
function saveMessage(message){
    const messageDTO = {
        chattingNo : message.room,
        messageContent : message.text,
        messageFrom : message.username
    }
    const requestInfo = {
        method : 'POST',
        headers : {
            'content-type' : 'application/json'
        },
        body : JSON.stringify(messageDTO)
    };
    fetch(`/api/v1/chat/messages`, requestInfo)
        .then(res => res.json())
        .then(flag => {
            if(flag) console.log('메세지 저장 성공');
            else console.log('메세지 저장 실패');
        })

}

// 채팅 중일때 메세지 실시간 렌더 및 DB 저장
export function renderAndSaveMessage(message){
    outputMessage(message);
    saveMessage(message);
}