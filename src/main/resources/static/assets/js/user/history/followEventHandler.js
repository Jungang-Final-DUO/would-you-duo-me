export function followEventHandler(userAccount, $followBtn) {

    $followBtn.addEventListener("click", followFetch);

}

function followFetch(e) {
    fetch('api/v1/user')
}