export function outputMessage(message) {
    const room = document.getElementById(message.room);

    const div = document.createElement('div');
    div.classList.add('chatting-message-card');

    if (message.username === '원영이') {
        div.classList.add('chatting-message-card message-from');
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
        div.classList.add('chatting-message-card message-to');
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