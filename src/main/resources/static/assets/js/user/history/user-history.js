import {drawPieGraph} from "./draw-pie-graph.js";

(() => {
    drawPieGraph(+document.getElementById('numeric-win-rate').dataset.winRate, '#win-rate-graph');
})();