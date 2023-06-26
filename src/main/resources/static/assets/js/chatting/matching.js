// 매칭요청을 하는 사람의 이벤트
import {selectDateEvent} from "./chatting-calendar.js";

export function matchingRequestEvent(){
    console.log('matchingRequestEvent 진입');
    const requestBtn = [...document.querySelectorAll('.matching-accept-btn')];
    console.log(requestBtn);
    requestBtn.forEach(
        mr => mr.onclick = async e => {
            const matchingStatus = e.target.closest('.matching-accept-btn').dataset.matchingStatus;
            console.log(matchingStatus);
            const $chattingNo = e.target.closest('.chatting-card').id;
            const $matchingNo = e.target.closest('.matching-accept-btn').dataset.matchingNo;
            switch (matchingStatus){
                case 'null' :
                    await matchingRequest($chattingNo);
                    sendNoticeMessage($chattingNo , '듀오 매칭을 요청합니다');
                    break;
                case 'CONFIRM' : gameDone($chattingNo, $matchingNo); break;
            }
        });
}

// 요청을 받은 사람의 이벤트
export function matchingResponseEvent(){
    const requestedBtn = [...document.querySelectorAll('.matching-accept-btn')];
    requestedBtn.forEach(
        mr => mr.onclick = e => {
            const matchingStatus = e.target.closest('.matching-accept-btn').dataset.matchingStatus;
            const $chattingNo = e.target.closest('.chatting-card').id;
            const $matchingNo = e.target.closest('.matching-accept-btn').dataset.matchingNo;
            console.log('온클릭 이벤트까지 넘어옴');
            switch (matchingStatus){
                case 'REQUEST' :
                    document.querySelector('.match-calendar').click();
                    selectDateEvent();
                    break;
                case 'DONE' :
                    getMatchingPoint($chattingNo, $matchingNo);
            }

        });
}

//작성중 !!
function getMatchingPoint($chattingNo, $matchingNo){
    const payload = {
        chattingNo : $chattingNo,
        matchingNo : $matchingNo
    }
    const requestInfo = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(payload)
    }

    fetch(`/api/v1/points/give`, requestInfo)
        .then(res => res.json())
        .then(point => receivedPoint($chattingNo, point));
}

function receivedPoint($chattingNo, point){
    console.log(point);
    alert(`${point} 포인트가 지급되었습니다!`);
    const chatCard = document.getElementById($chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    const handShakeImg = $target.querySelector('.chatting-handshake-img');
    handShakeImg.src = '/assets/img/chattingModal/handshake.png';
    handShakeImg.alt = '매칭수락이미지';
    $target.dataset.matchingStatus = '';
    $target.disabled = true;
    $target.childNodes[1].nodeValue = '매칭 대기';
    $target.dataset.matchingNo = '';
}


export function clickSelectDate($chattingNo, $matchingNo, matchingYear, matchingMonth, matchingDate){
    console.log('날짜선택 클릭까지 넘어옴');
    const selectBtn = document.getElementById('select-date');
    selectBtn.onclick = e => {
        if (confirm(matchingMonth + '월 ' + matchingDate + '일자로 매칭을 확정하시겠습니까?')) {
            // const selectedDate = new Date(+matchingYear, +matchingMonth, +matchingDate);
            console.log('확정됨');
            const selectedDate = matchingYear + "-" + matchingMonth + "-" + matchingDate;
            console.log(selectedDate);
            matchingConfirm($chattingNo, $matchingNo, selectedDate);
        }
    }

}

export function  matchingConfirm(chattingNo, matchingNo, selectedDate) {
    console.log('매칭 확정 fetch까지 넘어옴');
    const matchingFixRequestDTO = {
        matchingNo : matchingNo,
        matchingDate : selectedDate
    }
    const requestInfo = {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(matchingFixRequestDTO)
    }

    fetch(`/api/v1/matchings/fix`, requestInfo)
        .then(res => res.json())
        .then(result => {

            if(result === true){
                document.querySelector('.match-calendar').click();
                changeMatchingToConfirm(chattingNo);
                sendNoticeMessage(chattingNo, '매칭이 확정되었습니다.');
            }
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

    return fetch(`/api/v1/matchings`, requestInfo)
        .then(res => res.json())
        .then(result => {
            changeMatchingStatus(chattingNo, result);
        });
            // .then(
        //     chattingNo => sendNoticeMessage(chattingNo, '듀오 매칭을 요청합니다')
    // );
}

//REQUEST -> CONFIRM으로 상태 변경
function changeMatchingToConfirm(chattingNo){
    const chatCard = document.getElementById(chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.dataset.matchingStatus = 'CONFIRM';
    $target.disabled = true;
    $target.childNodes[1].nodeValue = '매칭 확정';
    //matchingResponseEvent();

    const btnBox = chatCard.querySelector('.chatting-message-option');
    btnBox.children[1].remove();

}

// CONFIRM -> DONE으로 상태 변경
function gameDone($chattingNo, $matchingNo){
    console.log($matchingNo);
    const requestInfo = {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: $matchingNo
    }

    fetch(`/api/v1/matchings/done`, requestInfo)
        .then(res => res.json())
        .then(result => {
            if(result === true){
                matchingDone($chattingNo);
                sendNoticeMessage($chattingNo, '즐거운 게임이었어요');
            }
        });
}

//'' -> REQUEST로 상태 변경
function changeMatchingStatus(chattingNo, result) {
    const chatCard = document.getElementById(chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.dataset.matchingStatus = 'REQUEST';
    $target.setAttribute('disabled', 'disabled');
    $target.dataset.matchingNo = result;
    $target.childNodes[1].nodeValue = '수락대기중';
    return chattingNo;
}

function matchingDone(chattingNo){
    console.log(chattingNo);
    const chatCard = document.getElementById(chattingNo);
    console.log(chatCard);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.disabled = false;
    $target.dataset.matchingStatus = 'DONE';
    $target.childNodes[1].nodeValue = '리뷰 쓰기';

}

function sendNoticeMessage(chattingNo, msg){
    console.log(chattingNo);
    const chat = document.getElementById(chattingNo);
    console.log(chat);
    // const name = chat.querySelector('div.message-to div.message-nickname').innerText;

    chat.querySelector('.msg').value = msg;
    chat.querySelector('.message-send-btn').click();
}

export function getRecentMatchingNo(chattingNo){

    fetch(`/api/v1/matchings/${chattingNo}`)
        .then(res => res.json())
        .then(result => {
            setMatchingNo(chattingNo, result);
        });

}

function setMatchingNo(chattingNo, matchingNo){
    const chatting = document.getElementById(chattingNo);
    chatting.querySelector('.matching-accept-btn').dataset.matchingNo = matchingNo;
}


