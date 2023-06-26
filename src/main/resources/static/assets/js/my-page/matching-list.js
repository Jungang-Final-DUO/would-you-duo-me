import {scrollPaging} from "../common/scroll-paging.js";

(() => {

    // 로그인한 사용자의 계정
    const userAccount = document.getElementById('loginUserInfo').dataset.userAccount;

    // 받은 리뷰 정보 페이지
    const increaseGottenReviewPage = (() => {
        let pageNo = 1;
        return ++pageNo;
    })();

    const increaseWrittenReviewPage = (() => {
        let pageNo = 1;
        return ++pageNo;
    })();

    // 페이지가 로드될 때 1 페이지 씩 렌더링
    renderGottenReviewOnMyPage(userAccount, 1).then();
    renderWrittenReviewOnMyPage(userAccount, 1).then();

    // 스크롤 이벤트 등록
    scrollPaging(document.getElementById('gotten-matching-wrapper'), renderGottenReviewOnMyPage,
        {
            userAccount: userAccount,
            pageNo: increaseGottenReviewPage
        });
    scrollPaging(document.getElementById('applied-matching-wrapper'), renderWrittenReviewOnMyPage,
        {
            userAccount: userAccount,
            pageNo: increaseWrittenReviewPage
        });
})();

async function renderGottenReviewOnMyPage(userAccount, pageNo) {
    const res = await fetch(`/api/v1/matchings/reviews/gotten/${userAccount}/${pageNo}`);

    const $wrapper = document.getElementById('gotten-matching-wrapper');

    if (res.status === 200) {
        const matchingList = (await res.json()).list;
        for (const matching of matchingList) {

            await renderMatchingList($wrapper, matching);

            if (matching.matchingReviewContent !== null) {

                const $review = document.createElement('div');
                $review.textContent = matching.matchingReviewContent;
                $wrapper.appendChild($review);

            } else {

                // 빈 칸을 위해 빈 태그 추가
                $wrapper.appendChild(document.createElement('div'));

            }

        }
    }
}

async function renderWrittenReviewOnMyPage(userAccount, pageNo) {
    console.log(userAccount, pageNo);

    const res = await fetch(`/api/v1/matchings/reviews/written/${userAccount}/${pageNo}`);

    const $wrapper = document.getElementById('applied-matching-wrapper');

    if (res.status === 200) {
        const matchingList = (await res.json()).list;
        for (const matching of matchingList) {

            await renderMatchingList($wrapper, matching);

            if (matching.matchingReviewContent !== null) {

                const $review = document.createElement('div');
                $review.textContent = matching.matchingReviewContent;
                $wrapper.appendChild($review);

            } else {

                const $btn = document.createElement('button');
                $btn.textContent = '리뷰 쓰러 가기';
                $btn.dataset.matchingNo = matching.matchingNo;
                $btn.classList.add('modal-btn');
                $wrapper.appendChild($btn);

            }

        }
    }
}

async function renderMatchingList($wrapper, {opponentNickname, matchingDate}) {
    const $opponent = document.createElement('div');
    $opponent.textContent = opponentNickname;
    $wrapper.appendChild($opponent);

    const $date = document.createElement('div');
    $date.textContent = matchingDate;
    $wrapper.appendChild($date);

}