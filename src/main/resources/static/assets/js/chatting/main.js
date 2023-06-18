import {outputMessage} from "./messageRendering.js";
const ChatForm = document.querySelectorAll('.chat-form');
const chatMessages = document.querySelectorAll('.chatting-message-body');
const username = '바보';
// const username = '원영이';

document.addEventListener("DOMContentLoaded", function () {
    const socket = io("http://localhost:3000");

    //Message from server
    socket.on('message', message => {

        //output message to DOM
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

            //Get message text and room
            const msg = e.target.querySelector('.msg').value;
            const room = e.target.closest('.chatting-card').id;

            //Emit message to server
            socket.emit('chatMessage', {username, room, msg});

            //clear message
            e.target.querySelector('.msg').value = '';
        });
    })

});