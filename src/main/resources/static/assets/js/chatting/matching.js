// 매칭요청을 하는 사람의 이벤트
import {selectDateEvent} from "./chatting-calendar.js";

export function matchingRequestEvent(){
    console.log('matchingRequestEvent 진입');
    const requestBtn = [...document.querySelectorAll('.matching-accept-btn')];
    console.log(requestBtn);
    requestBtn.forEach(
        mr => mr.onclick = e => {
            const matchingStatus = e.target.closest('.matching-accept-btn').dataset.matchingStatus;
            console.log(matchingStatus);
            const $chattingNo = e.target.closest('.chatting-card').id;

            switch (matchingStatus){
                case 'null' : matchingRequest($chattingNo); break;
                case 'CONFIRM' : gameDone($chattingNo); break;
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
            const $matchingNo = e.target.closest('.matching-accept-btn').matchingNo;
            console.log('온클릭 이벤트까지 넘어옴');
            switch (matchingStatus){
                case 'REQUEST' :
                    document.querySelector('.match-calendar').click();
                    selectDateEvent();
            }

        });
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

    fetch(`/api/v1/matchings`, requestInfo)
        .then(res => res.json())
        .then(result => {
            changeMatchingStatus(chattingNo, result);
            sendNoticeMessage(chattingNo, '듀오 매칭을 요청합니다');
        });
}

//REQUEST -> CONFIRM으로 상태 변경
function changeMatchingToConfirm(chattingNo){
    const chatCard = document.getElementById(chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.dataset.matchingStatus = 'CONFIRM';
    $target.disabled = true;
    $target.childNodes[1].nodeValue = '매칭 확정';

    const btnBox = chatCard.querySelector('.chatting-message-option');
    btnBox.children[1].remove();

}

// CONFIRM -> DONE으로 상태 변경
function gameDone(chattingNo){
    const requestInfo = {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        body: chattingNo
    }

    fetch(`/api/v1/done`, requestInfo)
        .then(res => res.json())
        .then(result => {
            if(result === true){
                matchingDone(chattingNo);
                sendNoticeMessage(chattingNo, '즐거운 게임이었어요');
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
}

function matchingDone(chattingNo){
    const chatCard = document.getElementById(chattingNo);
    const $target = chatCard.querySelector('.matching-accept-btn');
    $target.disabled = false;
    $target.dataset.matchingStatus = 'DONE';
    $target.childNodes[1].nodeValue = '게임 완료';

}

function sendNoticeMessage(chattingNo, msg){
    const chat = document.getElementById(chattingNo);
    const name = chat.querySelector('div.message-to div.message-nickname').innerText;

    chat.querySelector('.msg').value = msg;
    chat.querySelector('.message-send-btn').click();
}
