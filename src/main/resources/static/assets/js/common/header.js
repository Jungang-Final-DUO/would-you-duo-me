import {addModalBtnEvent, addModalCloseEvent} from "./modal-handler.js";
import {toBack} from "../chatting/chatting-modal.js";

(() => {
    addModalBtnEvent();
    addModalCloseEvent();
    toBack();
})();