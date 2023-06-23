import {renderAndSaveMessage, scrollDown} from "./messageRendering.js";
import {renderUnreadMessages} from "./chatting-modal.js";

export function connectSocket() {
    const socket = io("http://localhost:3000");

    const username = 'test1';

    socket.on('message', message => {

        //output message to DOM
        renderAndSaveMessage(message);
        renderUnreadMessages(message.room);
        scrollDown();
    });

    const $chatCard = document.querySelector('.chatting-modal-container');
    $chatCard.addEventListener('submit', e => {
        e.preventDefault();

        //Get message text and room
        const msg = e.target.querySelector('.msg').value;
        const room = e.target.closest('.chatting-card').id;

        //Emit message to server
        socket.emit('chatMessage', {username, room, msg});

        //clear message
        e.target.querySelector('.msg').value = '';
    });

}