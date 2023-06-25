import {outputMessage, saveMessage, scrollDown} from "./messageRendering.js";
import {renderUnreadMessages} from "./chatting-modal.js";

export function connectSocket() {
    const socket = io("http://localhost:3000");

    socket.on('message', message => {

        outputMessage(message);
        renderUnreadMessages(message.room);
        scrollDown();
    });

    const $chatCard = document.querySelector('.chatting-modal-container');
    $chatCard.addEventListener('submit', e => {
        e.preventDefault();

        //Get message text and room
        const username = document.getElementById('loginUserInfo').dataset.userNickname;
        const msg = e.target.querySelector('.msg').value;
        const room = e.target.closest('.chatting-card').id;
        const chatBox = document.getElementById(room);
        const matchingStatus = chatBox.querySelector('.matching-accept-btn').dataset.matchingStatus;
        const matchingNo = chatBox.querySelector('.matching-accept-btn').dataset.matchingNo;

        //Emit message to server
        socket.emit('chatMessage', {username, room, msg, matchingStatus, matchingNo});

        //output message to DOM
        saveMessage({username, room, msg, matchingStatus, matchingNo});

        //clear message
        e.target.querySelector('.msg').value = '';
    });

}