export function outputMessage(message) {
    const roomId = message.room;
    const room = document.getElementById(roomId);

    const div = document.createElement('div');
    div.classList.add('chatting-message-card');

    if (message.username === '원영이') {
        div.innerHTML = `<div class="chatting-message-card message-from">
                <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                <div class="message-content-container">
                    <div class="message-nickname">${message.username}</div>
                    <div class="message-content-wrapper">
                        <div class="message-content">${message.text}</div>
                        <span class="send-time">${message.time}</span>
                    </div>
                </div>
            </div>`;
    } else {
        div.innerHTML = `<div class="chatting-message-card message-to">
                <img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                <div class="message-content-container">
                        <div class="message-nickname">${message.username}</div>
                        <div class="message-content-wrapper">
                            <div class="message-content">${message.text}</div>
                            <span class="send-time">${message.time}</span>
                        </div>
                    </div>
                    </div>`;
    }

    room.querySelector('.chatting-message-body').appendChild(div);
}