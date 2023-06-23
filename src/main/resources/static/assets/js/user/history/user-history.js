import {drawPieGraph} from "./draw-pie-graph.js";
import {renderReviewRate} from "../../review/render-review-rate.js";
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
    renderReviewRate({
        $wrapper: $reviewWrapper,
        userAccount: userAccount,
        pageNo: increasePageNo
    }).then();

    // 스크롤 이벤트 등록
    const $reviewSection = document.getElementById('review-section');

    scrollPaging($reviewSection, renderReviewRate, {
        $wrapper: $reviewWrapper,
        userAccount: userAccount,
        pageNo: increasePageNo
    });


})();