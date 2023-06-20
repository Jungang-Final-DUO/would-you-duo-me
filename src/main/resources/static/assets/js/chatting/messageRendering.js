export function outputMessage(message) {
    const room = document.getElementById(message.room);
    console.log(room);
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

export function getMessages(chattingNo){

    const userId = 'test1';

    fetch(`/api/v1/chat/messages/${userId}/${chattingNo}`)
        .then(res => res.json())
        .then(result => {
            setChattingDetailBox(chattingNo, result);
        })
}

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

}