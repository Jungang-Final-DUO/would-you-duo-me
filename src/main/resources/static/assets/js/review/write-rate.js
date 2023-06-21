export async function renderRateModal(matchingNo, userNickname) {
    const $rateModal = document.createElement('dialog');
    $rateModal.innerHTML = `<div id="review-modal-background">
        <div id="review-modal-wrapper">
            <div id="review-modal-title">
                <input type="hidden" name="matchingNo" value="${matchingNo}">
                <div id="profile-image-frame"><img id="profile-image" src="/assets/img/chattingModal/young.jpg"
                                                   alt="profile"></div>
                <div id="nickname-rate">
                    <div id="user-nickname">${userNickname}</div>
                    <div id="give-rate" data-rate="5">
                        <img id="rate1" class="reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png"
                             alt="rate">
                        <img id="rate2" class="reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png"
                             alt="rate">
                        <img id="rate3" class="reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png"
                             alt="rate">
                        <img id="rate4" class="reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png"
                             alt="rate">
                        <img id="rate5" class="reviewRate rate-image" src="/assets/img/chattingModal/rate-filled.png"
                             alt="rate">
                    </div>
                </div>
            </div>
            <div id="review-modal-content">
                <label><textarea id="reviewContent" name="reviewContent"
                                 placeholder="게임은 어떠셨나요?&#10;솔직한 후기를 작성해주세요"></textarea><img id="send-review"
                                                                                             src="/assets/img/chattingModal/send-review.png"
                                                                                             alt="send-review"></label>
            </div>
        </div>
    </div>`;

    document.getElementById('send-review').onclick = async e => {
        const res = await fetch("/api/v1/matchings/reviews",
            {
                method: 'PUT',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    matchingNo: matchingNo,
                    reviewContent: document.getElementById('reviewContent')
                        .innerText,
                    reviewRate: +document.getElementById('give-rate').dataset.rate
                })
            });
        
        if (res.status === 200) {
            alert('리뷰 등록에 성공했습니다!');
        } else {
            alert(`리뷰 등록에 실패했습니다. 오류 메세지 : ${await res.text()}`);
        }
    }

    return $rateModal;
}