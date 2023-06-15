function closeRecentModal($toBack){
    $toBack.onclick = e => {
        const $input = document.getElementById('msg');
        $input.removeAttribute('autofocus');
        e.target.closest('.chatting-message-modal-wrapper').classList.add('invisible');
    }
}

export function toBack() {

    [...document.querySelectorAll('.toBack')].forEach(
        $toBack => closeRecentModal($toBack)
    );

}

export function setAutofocus(target){

    const $input = target.querySelector('#msg');

    $input.removeAttribute('autofocus');
    $input.setAttribute('autofocus', 'true');

}