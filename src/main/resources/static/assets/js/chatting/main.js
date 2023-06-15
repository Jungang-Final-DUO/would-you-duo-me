const ChatForm = document.querySelectorAll('.chat-form');
const chatMessages = document.querySelectorAll('.chatting-message-body');

document.addEventListener("DOMContentLoaded", function () {
    const socket = io("http://localhost:3000");

    //Message from server
    socket.on('message', message => {
        console.log(message);
        outputMessage(message);

        //scroll down
        chatMessages.forEach(cm => {
            cm.scrollTop = cm.scrollHeight;
        })
    });

    // Message submit
    ChatForm.forEach(cf => {
        cf.addEventListener('submit', (e) => {
            e.preventDefault();

            //Get message Text
            const msg = e.target.querySelector('.msg').value;

            //Emit message to server
            socket.emit('chatMessage', msg);
        });
    });

    //output message to DOM
    function outputMessage(message){
        const div = document.createElement('div');
        div.classList.add('chatting-message-card');
        div.innerHTML = `<img class="chatting-profile" src="/assets/img/chattingModal/woogi.jpg" alt="프로필이미지">
                    <div class="message-content-container">
                        <div class="message-nickname">${message.username}</div>
                        <div class="message-content-wrapper">
                            <div class="message-content">${message.text}</div>
                            <span class="send-time">${message.time}</span>
                        </div>
                    </div>`;
        document.querySelector('.chatting-message-body').appendChild(div);
    }
});