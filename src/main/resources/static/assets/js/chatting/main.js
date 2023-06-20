import {renderAndSaveMessage, scrollDown} from "./messageRendering.js";

export function connectSocket(ChatForm) {
    console.log('connectSocket 도달');

    const username = 'test1';
// const username = '원영이';
    const socket = io("http://localhost:3000");
    console.log(ChatForm);
    console.log(socket);

    //Message from server
    socket.on('message', message => {

        //output message to DOM
        renderAndSaveMessage(message);
        scrollDown();

    });
    // Message submit
    ChatForm.forEach(cf => {
        cf.addEventListener('submit', (e) => {
            e.preventDefault();
            // console.log(e.target);

            //Get message text and room
            const msg = e.target.querySelector('.msg').value;
            const room = e.target.closest('.chatting-card').id;

            //Emit message to server
            socket.emit('chatMessage', {username, room, msg});

            //clear message
            e.target.querySelector('.msg').value = '';
        });
    });
}