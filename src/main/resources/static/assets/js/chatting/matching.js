// 매칭요청을 하는 사람의 이벤트
export function matchingRequestEvent(){
    console.log('matchingRequestEvent 진입');
    const requestBtn = [...document.querySelectorAll('.matching-accept-btn')];
    console.log(requestBtn);
    requestBtn.forEach(
        mr => mr.onclick = e => {
            const matchingStatus = e.target.closest('.matching-accept-btn').dataset.matchingStatus;
            console.log(matchingStatus);
            const $chattingNo = e.target.closest('.chatting-card').id;
            if(matchingStatus === 'null'){
                matchingRequest($chattingNo);
            }
        });
}

// 요청을 받은 사람의 이벤트
export function matchingResponseEvent(){
    const requestedBtn = [...document.querySelectorAll('.matching-requested')];
    requestedBtn.forEach(
        mr => mr.onclick = e => {
            const $chattingNo = e.target.closest('.chatting-card').id;
            matchingConfirm($chattingNo);
        });
}

function  matchingConfirm(chattingNo) {
    const requestInfo = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: chattingNo
    }

    fetch(`/api/v1/matchings`, requestInfo)
        .then(res => res.json())
        .then(result => {
            changeMatchingStatus(chattingNo, result);
            sendNoticeMessage(chattingNo, '매칭이 신청되었습니다.');
        });
}

function matchingRequest(chattingNo){

    const requestInfo = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: chattingNo
    }

    fetch(`/api/v1/matchings`, requestInfo)
        .then(res => res.json())
        .then(result => {
            changeMatchingStatus(chattingNo, result);
            sendNoticeMessage(chattingNo, '듀오 매칭을 요청합니다');
        });


}

function changeMatchingStatus(chattingNo, result) {
    const chatCard = document.getElementById(chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.dataset.matchingStatus = 'REQUEST';
    $target.setAttribute('disabled', 'disabled');
    $target.dataset.matchingNo = result;
    $target.childNodes[1].nodeValue = '수락대기중';
}

function sendNoticeMessage(chattingNo, msg){
    const chat = document.getElementById(chattingNo);
    const name = chat.querySelector('div.message-to div.message-nickname').innerText;
    // const nowNow = new Date();
    // const nowMonth = nowNow.getMonth() + 1;
    // const nowDate = nowNow.getDate();
    // const nowHour = nowNow.getHours();
    // const nowMin = nowNow.getMinutes();

    chat.querySelector('.msg').value = msg;
    chat.querySelector('.message-send-btn').click();


    // const message = {
    //     room: chattingNo,
    //     text: msg,
    //     username: name,
    //     time: nowMonth + '.' + nowDate + ' ' + nowHour + ':' + nowMin
    // }
    // renderAndSaveMessage(message);
    // scrollDown();
}
