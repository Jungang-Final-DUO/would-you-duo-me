const ChatForm = document.getElementById('chat-form');

document.addEventListener("DOMContentLoaded", function () {
    const socket = io("http://localhost:3000");

    socket.on('message', message => {
        console.log(message);
    });

    // Message submit
    ChatForm.addEventListener('submit', (e) => {
       e.preventDefault();

       //Get message Text
       const msg = e.target.elements.msg.value;
        console.log(msg);

    //    //Emit message to server
    //     socket.emit('chattingMessage', msg);
    });
});