function closeRecentModal($toBack){
    $toBack.onclick = e => {
        e.target.closest('.chatting-message-modal-wrapper').classList.add('invisible');
    }
}

export function toBack() {

    [...document.querySelectorAll('.toBack')].forEach(
        $toBack => closeRecentModal($toBack)
    );

}