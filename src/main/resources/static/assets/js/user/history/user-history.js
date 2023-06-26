import {drawPieGraph} from "./draw-pie-graph.js";
import {renderReview} from "../../review/render-review.js";
import {scrollPaging} from "../../common/scroll-paging.js";

(() => {
    // 페이지 번호를 위한 클로저
    const increasePageNo = (() => {
        let pageNo = 0;
        return () => ++pageNo;
    })();

    const userAccount = document.getElementById('history-wrapper').dataset.userAccount;

    // 승률 그래프 그리기
    drawPieGraph(+document.getElementById('numeric-win-rate').dataset.winRate, '#win-rate-graph');

    // 리뷰 1페이지 불러오기
    const $reviewWrapper = document.getElementById('review-wrapper');
    renderReview({
        $wrapper: $reviewWrapper,
        userAccount: userAccount,
        pageNo: increasePageNo
    }).then();

    // 스크롤 이벤트 등록
    const $reviewSection = document.getElementById('review-section');

    scrollPaging($reviewSection, renderReview, {
        $wrapper: $reviewWrapper,
        userAccount: userAccount,
        pageNo: increasePageNo
    });


})();