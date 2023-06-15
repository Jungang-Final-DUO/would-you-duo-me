import {setAutofocus} from "../chatting/chatting-modal.js";

function modalHandler($dropDownBtn) {

    $dropDownBtn.onclick = e => {
        e.preventDefault();
        const target = e.target.closest('.modal-btn').nextElementSibling;
        target.classList.toggle('invisible');
        setAutofocus(target);
    }
}

export function addModalBtnEvent() {

    [...document.querySelectorAll('.modal-btn')].forEach(
        $modalBtn => modalHandler($modalBtn)
    );

}