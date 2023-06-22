import {addModalBtnEvent, addModalCloseEvent, modalHandler} from "../common/modal-handler.js";
import {renderRateModal} from "../review/write-rate.js";

export function getChattingList() {
    // 추후 session에 회원정보 담기면 경로 수정
    const userId = 'test1';
    fetch(`/api/v1/chat/chattings/${userId}`)
        .then(res => res.json())
        .then(result => renderChattingList(result))
}

// 채팅 클릭했을때 메세지창 열어줘야함
function makeChatting(chat) {
    const userId = 'test1';

    chat.onclick = () => {
        const userAccount = chat.closest('.duo-profile').id;
        console.log('makeChatting 도달');
        const requestInfo = {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: userId
        }

        fetch(`/api/v1/chat/chattings/${userAccount}`, requestInfo)
            .then(res => res.json())
            .then(result => {
                // result = 생성된 채팅번호
                getChattingList();
                document.getElementById('chatting-btn').click();
            });
    }
}


export function makeChattingRoom() {
    [...document.querySelectorAll('.chatting-icon')].forEach(
        chat => makeChatting(chat)
    );
}

function renderChattingList(result) {
    document.querySelector('.chatting-modal-container').innerHTML = '';

    if (result.length === 0) {
        const $chattings = document.createElement('li');
        $chattings.id = 'empty-chatting-list';
        $chattings.innerHTML = '아직 채팅 내역이 없어요.<br>다른 듀오 회원에게 말을 걸어보세요!';
        document.querySelector('.chatting-modal-container').appendChild($chattings);

    } else {
        for (let i = 0; i < result.length; i++) {

            const {
                chattingNo,
                chattingFrom,
                profileImage,
                userNickname,
                messageContent,
                messageUnreadCount,
                matchingStatus,
                matchingNo,
            } = result[i];

            let $chattings = document.createElement('li');
            $chattings.id = chattingNo;
            $chattings.classList.add('chatting-card');

            const $chat_card = document.createElement('div');
            $chat_card.classList.add('chat-card');

            const $modal_btn = document.createElement('div');
            $modal_btn.classList.add('chatting-card-inner');
            $modal_btn.classList.add('modal-btn');

            $modal_btn.innerHTML = `<img src="/assets/img/chattingModal/woogi.jpg" alt="프로필 이미지" class="chatting-profile-img">
                <div class="chatting-info">
                    <div class="chatting-nickname">${userNickname}</div>
                    <div class="chatting-current-message">${messageContent}</div>
                </div>
                <div class="chatting-unread">${messageUnreadCount}</div>`;

            $chattings.appendChild($chat_card);
            $chat_card.appendChild($modal_btn);

            const $dialog = document.createElement('dialog');
            $dialog.classList.add('message-dialog');
            $chat_card.appendChild($dialog);

            const $chatting_message_modal_wrapper = document.createElement('div');
            $chatting_message_modal_wrapper.classList.add('chatting-message-modal-wrapper');
            $dialog.appendChild($chatting_message_modal_wrapper);

            const $chatting_message_modal_container = document.createElement('div');
            $chatting_message_modal_container.classList.add('chatting-message-modal-container');
            $chatting_message_modal_wrapper.appendChild($chatting_message_modal_container);

            const $chatting_message_head = document.createElement('div');
            $chatting_message_head.classList.add('chatting-message-head');
            const chatting_message_option = document.createElement('div');
            chatting_message_option.classList.add('chatting-message-option');
            const chatting_message_body = document.createElement('div');
            chatting_message_body.classList.add('chatting-message-body');
            const chatForm = document.createElement('form');
            chatForm.classList.add('chatting-message-input-box');
            chatForm.classList.add('chat-form');

            $chatting_message_modal_container.appendChild($chatting_message_head);
            $chatting_message_modal_container.appendChild(chatting_message_option);
            $chatting_message_modal_container.appendChild(chatting_message_body);
            $chatting_message_modal_container.appendChild(chatForm);

            const $toBack = document.createElement('img');
            $toBack.classList.add('chatting-arrow-img');
            $toBack.classList.add('toBack');
            $toBack.src = '/assets/img/chattingModal/arrows.png';
            $toBack.alt = '뒤로가기';
            $chatting_message_head.appendChild($toBack);

            const chatting_message_nickname = document.createElement('div');
            chatting_message_nickname.classList.add('chatting-message-nickname');
            chatting_message_nickname.textContent = userNickname;
            $chatting_message_head.appendChild(chatting_message_nickname);

            // 로그인 한 사람이 Chatting From일 때
            // if(chattingFrom === 'test1') {
                const matching_accept_container = document.createElement('div');
                matching_accept_container.classList.add('matching-accept-container');
                chatting_message_option.appendChild(matching_accept_container);

                const matching_accept_btn = document.createElement('button');
                matching_accept_btn.classList.add('matching-accept-btn');
                matching_accept_btn.classList.add('matching-request');
                matching_accept_btn.dataset.matchingNo = '';
                matching_accept_container.appendChild(matching_accept_btn);

                const chatting_handshake_img = document.createElement('img');
                chatting_handshake_img.classList.add('chatting-handshake-img');
                chatting_handshake_img.src = '/assets/img/chattingModal/handshake.png';
                chatting_handshake_img.alt = '매칭수락이미지';
                matching_accept_btn.appendChild(chatting_handshake_img);
                matching_accept_btn.append('매칭신청');

                let $rightBtn = document.createElement('button');
                $rightBtn.classList.add('gameover-btn');

                switch (matchingStatus) {
                    case 'REQUEST':
                        $rightBtn.disabled = true;
                        $rightBtn.textContent = `수락 대기중`;
                        break;
                    case 'CONFIRM':
                        $rightBtn.textContent = `게임 완료`;
                        break;
                    case 'REJECT':
                        $rightBtn.textContent = `매칭 신청`;
                        break;
                    case 'DONE':
                        $rightBtn.innerHTML = `<img class="chatting-gameover-img" src="/assets/img/chattingModal/checkmark.png" alt="게임완료이미지">후기 작성`;
                        $rightBtn.onclick = async e => {
                            const $rateModal = await renderRateModal(matchingNo, userNickname);
                            document.body.appendChild($rateModal);
                            $rateModal.show();
                        }
                        break;
                }
            // }
            // const gameover_container = document.createElement('div');
            // gameover_container.classList.add('gameover-container');
            // chatting_message_option.appendChild(gameover_container);
            //
            // const gameover_btn = document.createElement('button');
            // gameover_btn.classList.add('gameover-btn');
            // gameover_container.appendChild(gameover_btn);
            //
            // const chatting_gameover_img = document.createElement('img');
            // chatting_gameover_img.classList.add('chatting-gameover-img');
            // chatting_gameover_img.src = '/assets/img/chattingModal/checkmark.png';
            // chatting_gameover_img.alt = '게임완료이미지';
            // gameover_btn.appendChild(chatting_gameover_img);
            // gameover_btn.append('게임완료');


            const message_send_box = document.createElement('input');
            message_send_box.classList.add('message-send-box');
            message_send_box.classList.add('msg');
            message_send_box.placeholder = '메시지를 입력해주세요';
            message_send_box.setAttribute('required', 'required');
            message_send_box.setAttribute('autofocus', 'autofocus');
            chatForm.appendChild(message_send_box);

            const message_send_btn = document.createElement('button');
            message_send_btn.classList.add('message-send-btn');
            message_send_btn.textContent = '전송';
            chatForm.appendChild(message_send_btn);

            document.querySelector('.chatting-modal-container').appendChild($chattings);

        }
        addModalBtnEvent();
        addModalCloseEvent();
        toBack();
    }
}

export function toBack() {

    [...document.querySelectorAll('.toBack')].forEach(
        $toBack => $toBack.onclick = e => {
            e.target.closest('.chatting-modal-dialog').close();
            document.getElementById('chatting-btn').click();
        }
    );
}

export function renderUnreadMessages(chattingNo) {
    const $chatting = document.getElementById(chattingNo);
    // console.log($chatting);
    const $target = $chatting.querySelector('.chatting-unread');
    // console.log($target);

    const userId = "test1";
    fetch(`/api/v1/chat/messages/unread/${userId}/${chattingNo}`)
        .then(res => res.json())
        .then(unread => {
            $target.innerText = unread;
        })
}
