import {drawPieGraph} from "./draw-pie-graph.js";
import {renderReviewRate} from "../../review/render-review-rate.js";

(() => {
    const userAccount = document.getElementById('history-wrapper').dataset.userAccount;

    // 승률 그래프 그리기
    drawPieGraph(+document.getElementById('numeric-win-rate').dataset.winRate, '#win-rate-graph');

    // 리뷰 1페이지 불러오기
    renderReviewRate(document.getElementById('review-wrapper'), userAccount, 1).then();
})();