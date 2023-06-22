// import {getChampionImg} from "../common/get-champion-img";

// 현재까지 렌더링된 페이지
let page = 1;
// 프로필카드 불러오는 URL
const profileCardListURL = "/api/v1/users";

const keyword = 'all';
const size = 40;
let position = 'all';
const $positionOption = document.querySelectorAll(".select-position"); // 포지션 선택 라디오 버튼
let gender = 'all';
const $genderOption = document.querySelectorAll(".select-gender");  // 성별 선택 라디오 버튼
let tier = 'all';
const $tierOption = document.querySelectorAll(".select-tier");  // 티어 선택 라디오 버튼
const sort = document.getElementById("order-list").value;


// 포지션 선택시 작동 함수
function selectPosition() {

    document.getElementById('searchBy-position').onclick = e => {
        
        if (e.target.classList.contains('select-position')) {
            console.log("포지션 클릭성공" + e.target);
            // 체크된 버튼 있는지 확인
            for (let i = 0; i < $positionOption.length; i++) {
                if ($positionOption[i].checked) {
                    position = $positionOption[i].value;
                    console.log(position);
                }
            }
        }
        getProfileCardList();
    }
}

// 성별 클릭시 작동 함수
function selectGender() {

    document.getElementById('searchBy-gender').onclick = e => {
        e.stopPropagation();
        if (e.target.classList.contains('select-gender')) {
            console.log("성별 클릭성공" + e.target);
            // 체크된 성별 있는지 확인
                for (let i = 0; i < $genderOption.length; i++) {
                    if ($genderOption[i].checked) {
                        gender = $genderOption[i].value;
                        console.log(gender);
                    }
                }
        }
        getProfileCardList();    
    }
}

// 티어 클릭시 작동 함수
function selectTier() {

    document.getElementById('searchByTier').onclick = e => {
        e.stopPropagation();
        if (e.target.classList.contains('select-tier')) {
            console.log("티어 클릭 성공" + e.target);
            // 체크된 티어 있는지 확인
            for (let i = 0; i < $tierOption.length; i++) {
                if ($tierOption[i].checked) {
                    tier = $tierOption[i].value;
                    console.log(tier);
                }
            }
        }
        getProfileCardList();
    }
}

// 검색 키워드 입력시 작동 함수
function searchName() {

    document.getElementById('searchBy-nickname').onkeyup = e => {
        
        console.log("키워드 입력중" + e.target.value);
        keyword = document.getElementById('searchBy-nickname').value;
        getProfileCardList();
    }
}

function getProfileCardList() { 
    let profileCardTag = '';
    console.log("fetch도착");
    fetch(profileCardListURL +'/' + page + '/' + keyword + '/' + size + '/' + position + '/' + gender + '/' + tier + '/' + sort)
    .then(res => res.json())
    .then(resResult => {
        // ====================================================================================
        console.log(resResult);
        for (let rep of resResult) {
            const {avgRate, followed, mostChampList, profileImage, tier, userAccount, userComment, userFacebook, userGender, userInstagram, userMatchingPoint, userNickname, userPosition, userTwitter} = rep;
            console.log("rep : "+ rep);
            // const {mostOne, mostTwo, mostThree} = mostChampList;
            // console.log(mostOne.champName +' '+ mostTwo.champName +' '+ mostThree.champName);
            // profileCardTag += <div id = "test4" class="duo-profile">
            //                         <img class="duo-tier" src="/assets/img/main/TFT_Regalia_Challenger.png" alt="tier">
                                    
            //                         <div class="profile-left-side">
            //                             <div class="profile-frame">
            //                                 <img class="profile-image" src="/assets/img/main/profile-image.jpg" alt="프로필 이미지">
            //                             </div>
            //                             <div class="profile-sns">
            //                                 <a href="#" class="sns-type instagram"><img class="sns-image" src="/assets/img/main/instagram.png"
            //                                                                             alt="instagram"></a>
            //                                 <a href="#" class="sns-type facebook"><img class="sns-image" src="/assets/img/main/facebook.png"
            //                                                                         alt="facebook"></a>
            //                                 <a href="#" class="sns-type twitter"><img class="sns-image" src="/assets/img/main/twitter.png"
            //                                                                         alt="twitter"></a>
            //                                 <div class="sns-type chatting-icon"><img class="sns-image" src="/assets/img/main/chatting-icon.png"
            //                                                                         alt="chatting"></div>
            //                             </div>
            //                         </div>
                                    
            //                         <div class="profile-right-side">
            //                             <div class="position-nickname">
            //                                 <img class="preferred-position" src="/assets/img/main/MID.png" alt="포지션">
            //                                 <p class="user-nickname">jhlee0622</p>
            //                                 <img class="follow-status" src="/assets/img/main/not-following.png" alt="following">
            //                             </div>
            //                             <div class="rate-matching-point">
            //                                 <div class="rate-matching-point rate-point-box"><img class="rate-matching-point-image"
            //                                                                                     src="/assets/img/main/star.png" alt="rate">
            //                                     <p class="avg-rate">89.2</p></div>
            //                                 <div class="rate-matching-point matching-point-box"><img class="rate-matching-point-image"
            //                                                                                         src="/assets/img/main/coin.png" alt="coin">
            //                                     <p class="matching-point">500</p></div>
            //                             </div>
            //                             <div class="profile-comment"><p>여기에 자기소개를 적을거에요^^ 같이 게임해용~~~</p></div>
            //                             <div class="profile-most-champ">
            //                                 <ul class="champ-list">
            //                                     <li class="most-pic first-champ"><img src="/assets/img/main/Skarner_3.jpg" alt="first-champ">
            //                                     </li>
            //                                     <li class="most-pic second-champ"><img src="/assets/img/main/TahmKench_0.jpg"
            //                                                                         alt="second-champ"></li>
            //                                     <li class="most-pic third-champ"><img src="/assets/img/main/Ziggs_2.jpg" alt="third-champ"></li>
            //                                 </ul>
            //                             </div>
            //                         </div>
            //                     </div>
        
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
    // getProfileCardList();

})();