export function addUserInfoBtnHandler() {
    document.getElementById('user-info-btn').addEventListener('click', async e => {
        const res = await fetch('/api/v1/users/infos');

        if (res.status === 200) {
            renderUserInfo(await res.json());
        }
    });
}

function renderUserInfo({profileImage,
                        userNickname,
                        lolNickname,
                        userCurrentPoint}) {
    document.getElementById('profile-img-wrapper').firstElementChild.src = profileImage;
    document.getElementById('nickname-wrapper').textContent = userNickname;
    const $userInfo = document.getElementById('user-info-wrapper');
    $userInfo.firstElementChild.textContent = ` 라이엇 계정 : ${lolNickname} `;
    $userInfo.lastElementChild.textContent = ` 잔여 포인트 : ${userCurrentPoint} point `;

}