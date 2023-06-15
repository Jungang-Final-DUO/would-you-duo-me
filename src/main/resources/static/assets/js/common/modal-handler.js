function modalHandler($dropDownBtn) {

    $dropDownBtn.onclick = e => {
        e.preventDefault();

        e.target.closest('.modal-btn').nextElementSibling.classList.toggle('close');

    }
}

export function addModalBtnEvent() {

    [...document.querySelectorAll('.modal-btn')].forEach(
        $modalBtn => modalHandler($modalBtn)
    );

}