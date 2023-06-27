import { getChampionImg } from "../common/get-champion-img.js";

// 현재까지 렌더링된 페이지
let page = 1;
// 프로필카드 불러오는 URL
const profileCardListURL = "/api/v1/users";

let keyword = '';
const size = 40;
let position = 'all';
const $positionOption = document.querySelectorAll(".select-position"); // 포지션 선택 라디오 버튼
let gender = 'all';
const $genderOption = document.querySelectorAll(".select-gender");  // 성별 선택 라디오 버튼
let tier = 'all';
const $tierOption = document.querySelectorAll(".select-tier");  // 티어 선택 라디오 버튼
const sort = document.getElementById("order-list").value;

// 프로필 카드 내부 자식 태그들 전부 지우는 함수
function removeTag() {
    const $profileCardWrapper = document.getElementById('profile-cards-wrapper');

    while($profileCardWrapper.firstChild) {
        $profileCardWrapper.removeChild($profileCardWrapper.firstChild);
    }
}

// 포지션 선택시 작동 함수
function selectPosition() {

    document.getElementById('searchBy-position').onclick = e => {

        removeTag();
        
        if (e.target.classList.contains('select-position')) {
            console.log("포지션 클릭성공" + e.target);
            // 체크된 버튼 있는지 확인
            for (let i = 0; i < $positionOption.length; i++) {
                if ($positionOption[i].checked) {
                    position = $positionOption[i].value;
                    console.log(position);
                    getProfileCardList();
                }
            }
        }
    }
}

// 성별 클릭시 작동 함수
function selectGender() {

    document.getElementById('searchBy-gender').onclick = e => {

        removeTag();
        e.stopPropagation();
        if (e.target.classList.contains('select-gender')) {
            console.log("성별 클릭성공" + e.target);
            // 체크된 성별 있는지 확인
                for (let i = 0; i < $genderOption.length; i++) {
                    if ($genderOption[i].checked) {
                        gender = $genderOption[i].value;
                        console.log(gender);
                        getProfileCardList();    
                    }
                }
        }
    }
}

// 티어 클릭시 작동 함수
function selectTier() {

    document.getElementById('searchByTier').onclick = e => {

        removeTag();
        e.stopPropagation();
        if (e.target.classList.contains('select-tier')) {
            console.log("티어 클릭 성공" + e.target);
            // 체크된 티어 있는지 확인
            for (let i = 0; i < $tierOption.length; i++) {
                if ($tierOption[i].checked) {
                    tier = $tierOption[i].value;
                    console.log(tier);
                    getProfileCardList();
                }
            }
        }
    }
}

// 검색 키워드 입력시 작동 함수
function searchName() {

    document.getElementById('searchBy-nickname').onkeyup = e => {

        removeTag();
        
        console.log("키워드 입력중" + e.target.value);
        keyword = document.getElementById('searchBy-nickname').value;
        getProfileCardList();
    }
}

function selectSort() {

    document.getElementById('order-list').onchange = e => {
        getProfileCardList();
    }
}

// tier 변환 함수 - 티어 이미지를 넣기 위한 함수
function transTier(tier) {
    switch (tier) {
        case 'CHA':
            return "Challenger";
            break;
        case 'IRO':
            return "Iron";
            break;
        case 'BRO':
            return "Bronze";
            break;
        case 'SIL':
            return "Silver";
            break;
        case 'GOL':
            return "Gold";
            break;
        case 'PLA':
            return "Platinum";
            break;
        case 'DIA':
            return "Diamond";
            break;
        case 'MAS':
            return "Master";
            break;
        case 'GRA':
            return "GrandMaster";
            break;
        default:
            return null;
    }
}

// 프로필이미지가 null인지 확인 후 null일 경우 기본이미지 삽입
function checkProfile(profileImage) {
    return profileImage === "basic" ? "/assets/img/main/basic-profile.png" : profileImage;
}

// 프로필 카드 비동기 요청 렌더링 함수
function getProfileCardList() { 
    let profileCardTag = '';
    let mostOne = '';
    let mostTwo = '';
    let mostThree = '';
    let changedTier = '';
    // console.log("fetch도착");
    // console.log("키워드"+keyword);

    if(keyword === '') keyword = '-';
    // console.log("강제키워드"+keyword);

    fetch(profileCardListURL +'/' + page + '/' + keyword + '/' + size + '/' + position + '/' + gender + '/' + tier + '/' + sort)
    .then(res => res.json())
    .then(resResult => {
        // ====================================================================================
        console.log(resResult);
        for (let rep of resResult) {
            const {avgRate, followed, mostChampList, profileImage, tier, userAccount, userComment, userFacebook, userGender, userInstagram, userMatchingPoint, userNickname, userPosition, userTwitter} = rep;
            for (let i = 0; i < mostChampList.length; i++) {
                if (mostChampList[i].mostNo === 1) mostOne = mostChampList[i].champName;
                else if (mostChampList[i].mostNo === 2) mostTwo = mostChampList[i].champName;
                else mostThree = mostChampList[i].champName;
            }
            changedTier = transTier(tier);
            console.log(mostOne + mostTwo + mostThree);
            
            profileCardTag += '<div id = "'+ userAccount +'" class="duo-profile">'
                                   + '<img class="duo-tier" src="/assets/img/main/TFT_Regalia_'+ changedTier +'.png" alt="tier">'
                                    
                                   + '<div class="profile-left-side">'
                                      + '<div class="profile-frame">'
                                           + '<img class="profile-image" src="'+ checkProfile(profileImage) +'" alt="프로필 이미지">'
                                       + '</div>'
                                      + '<div class="profile-sns">'
                                           + '<a href="'+ userInstagram +'" class="sns-type instagram"><img class="sns-image" src="/assets/img/main/instagram.png" alt="instagram"></a>'
                                           + '<a href="'+ userFacebook +'" class="sns-type facebook"><img class="sns-image" src="/assets/img/main/facebook.png" alt="facebook"></a>'
                                           + '<a href="'+ userTwitter +'" class="sns-type twitter"><img class="sns-image" src="/assets/img/main/twitter.png" alt="twitter"></a>'
                                           + '<div class="sns-type chatting-icon"><img class="sns-image" src="/assets/img/main/chatting-icon.png" alt="chatting"></div>'
                                       + '</div>'
                                   + '</div>'
                                    
                                   + '<div class="profile-right-side">'
                                      + '<div class="position-nickname">'
                                           + '<img class="preferred-position" src="/assets/img/main/'+ userPosition +'.png" alt="포지션">'
                                           + '<p class="user-nickname">'+ userNickname +'</p>'
                                           + '<img class="follow-status" src="/assets/img/main/not-following.png" alt="following">'
                                       + '</div>'
                                       + '<div class="rate-matching-point">'
                                           + '<div class="rate-matching-point rate-point-box"><img class="rate-matching-point-image" src="/assets/img/main/star.png" alt="rate">'
                                               + '<p class="avg-rate">'+ avgRate +'</p></div>'
                                           + '<div class="rate-matching-point matching-point-box"><img class="rate-matching-point-image" src="/assets/img/main/coin.png" alt="coin">'
                                               + '<p class="matching-point">'+ userMatchingPoint +'</p></div>'
                                       + '</div>'
                                       + '<div class="profile-comment"><p>'+ userComment +'</p></div>'
                                       + '<div class="profile-most-champ">'
                                           + '<ul class="champ-list">'
                                               + '<li class="most-pic first-champ"><img src="'+ getChampionImg(mostOne) +'" alt="first-champ"></li>'
                                               + '<li class="most-pic second-champ"><img src="'+ getChampionImg(mostTwo) +'" alt="second-champ"></li>'
                                               + '<li class="most-pic third-champ"><img src="'+ getChampionImg(mostThree) +'" alt="third-champ"></li>'
                                           + '</ul>'
                                       + '</div>'
                                   + '</div>'
                               + '</div>'

            document.getElementById('profile-cards-wrapper').innerHTML = profileCardTag;                   
        
        }
        // ====================================================================================
    });

 } 

//  function countPage() {
//     if (document.getElementById('profile-cards-wrapper').scrollTop === document.getElementById('profile-cards-wrapper').scrollHeight) {
//         page += 1;
//     }
//     getProfileCardList();
//  }


//========= 메인 실행부 =========//
(function () {

    selectPosition();
    selectGender();
    selectTier();
    searchName();   
    // countPage();

    // 프로필 카드 불러오기 함수(비동기)
    getProfileCardList();

})();