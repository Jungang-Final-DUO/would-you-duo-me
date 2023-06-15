function modalHandler($dropDownBtn) {

    $dropDownBtn.onclick = e => {
        e.preventDefault();
        const $target = e.target.closest('.modal-btn').nextElementSibling;

        if (!$target.hasAttribute('open'))
            $target.showModal();
        else
            $target.close();
    }
}

export function addModalBtnEvent() {

    [...document.querySelectorAll('.modal-btn')].forEach(
        $modalBtn => modalHandler($modalBtn)
    );

}

export function addModalCloseEvent() {

    [...document.querySelectorAll('dialog')].forEach(
        $dialog => $dialog.onclick = e => {
            if (e.target.matches('dialog'))
                $dialog.close();
        }
    )

}