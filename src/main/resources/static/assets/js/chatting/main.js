import {renderAndSaveMessage, scrollDown} from "./messageRendering.js";

export function connectSocket() {
    const socket = io("http://localhost:3000");

    console.log('connectSocket 도달');
    const username = 'test1';

    socket.on('message', message => {
        console.log('message 출력 진입');

        //output message to DOM
        renderAndSaveMessage(message);
        scrollDown();
    });

    const $chatCard = document.querySelector('.chatting-modal-container');
    $chatCard.addEventListener('submit', e => {
        e.preventDefault();
        console.log('submit 이벤트 진입');
        console.log(e.target);

        //Get message text and room
        const msg = e.target.querySelector('.msg').value;
        const room = e.target.closest('.chatting-card').id;

        //Emit message to server
        socket.emit('chatMessage', {username, room, msg});

        //clear message
        e.target.querySelector('.msg').value = '';
    });

}