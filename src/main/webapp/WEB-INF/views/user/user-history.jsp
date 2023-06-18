<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <%@ include file="../common/static-head.jsp" %>

    <link rel="stylesheet" href="/assets/css/user-history/user-history.css">

    <script type="module" src="/assets/js/user-history/user-history.js" defer></script>

</head>

<body>

<div id="main-wrapper">

    <!-- 헤더가 위치할 자리 -->
    <%@ include file="../common/header.jsp" %>

    <div id="history-wrapper">
        <div id="user-profile-info-wrapper">
            <div id="user-main-info-wrapper">
                <!-- 임시 이미지를 인라인 스타일로 먹임 -->
                <button id="profile-img-wrapper" class="btn"></button>
                <div>
                    <div id="user-nickname-etc-wrapper">
                        <!-- 달러랑 중괄호는 살짝 아래로 내려가있기 때문에 중앙정렬이 안맞아 보이는 것-->
                        <div id="user-nickname">${userAccount}</div>
                        <div id="user-position">
                            <img src="/assets/img/main/TOP.png" alt="탑">
                        </div>
                        <div id="user-follow-heart">
                            <button class="btn">
                                <!-- 팔로우 했을 경우 -->
                                <img src="/assets/img/main/following.png" alt="팔로우 시 하트">
                                <!-- 팔로우 하지 않았을 경우 -->
                                <!-- <img src="/assets/img/main/not-following.png" alt="언팔로우 시 하트">-->
                            </button>
                        </div>
                    </div>
                    <!-- end of user-nickname-etc-wrapper -->
                    <div id="rate-point-wrapper">
                        <div id="rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점 아이콘">
                            <span>${userAvgRate}</span>
                        </div>
                        <div id="point-wrapper">
                            <img src="/assets/img/main/coin.png" alt="포인트 아이콘">
                            <span>${userMatchingPoint}</span>
                        </div>
                    </div>
                    <!-- end of rate point wrapper -->
                    <div id="sns-chat-btn-wrapper">
                        <a href="https://instagram.com/${userInstagram}" class="btn">
                            <img src="/assets/img/main/instagram.png" alt="인스타그램 아이콘">
                        </a>
                        <a href="https://facebook.com/${userFacebook}" class="btn">
                            <img src="/assets/img/main/facebook.png" alt="페이스북 아이콘">
                        </a>
                        <a href="https://twitter.com/${userTwitter}" class="btn">
                            <img src="/assets/img/main/twitter.png" alt="트위터 아이콘">
                        </a>
                        <button class="btn">
                            <img src="/assets/img/main/chatting-icon.png" alt="채팅 아이콘">
                        </button>
                    </div>
                    <!-- end of sns chat button wrapper-->
                </div>
            </div>
            <!--            end of user main info wrapper-->
            <div id="lol-nickname-comment-wrapper">
                <div id="lol-nickname-wrapper">${lolNickname}</div>
                <div id="user-comment">${userComment}
                </div>
            </div>
        </div>
        <!--        end of user profile info wrapper-->
        <div id="ranking-info-wrapper">
            <div id="ranking-win-rate-wrapper">
                <div id="ranking-wrapper">
                    <div id="tier-wrapper">
                        <span>솔로 랭크</span>
                        <c:if test="tier ne null">
                            <img src="${"/assets/img/main/TFT_Regalia_" + tier + ".png"}" alt="티어 아이콘">
                        </c:if>
                        <c:if test="tier eq null">
                            <img src="${"/assets/img/main/unranked-removebg-preview.png"}" alt="티어 아이콘">
                        </c:if>
                    </div>
                </div>
                <div id="rank-etc-wrapper">
                    <div id="rank-point-info-wrapper">
                        <span>${tier}</span>
                        <span>${leaguePoints}LP</span>
                    </div>
                    <div id="simple-win-rate-wrapper">
                        <span>${totalWinCount}승 ${totalLostCount}패</span>
                        <span>승률 ${winRate}%</span>
                    </div>
                </div>
            </div>
            <!-- end of ranking and win rate wrapper -->
            <div id="detail-win-rate-wrapper">
                <div id="win-rate-graph-wrapper">
                    <div>20전 ${last20WinCount}승 ${last20LostCount}패</div>
                    <div id="win-rate-graph">
                        <span id="numeric-win-rate">${last20WinRate}%</span>
                    </div>
                </div>
                <div id="avg-point-wrapper">
                    <div id="avg-point">평점</div>
                    <div id="avg-kda">${last20KDA}</div>
                    <div id="avg-detail-kda">${last20Kill} / ${last20Death} / ${last20Assist}</div>
                </div>
                <div id="most-champ-wrapper">
                    <div>선호 챔피언 (최근 20 게임)</div>
                    <div id="most-champ-grid-wrapper">
                        <c:forEach var="champ" items="mostChampInfos">
                            <img src="/assets/img/user-history/champions/${champ.champName}_0.jpg" alt="모스트 챔피언 아이콘">
                            <div>${champ.champName}</div>
                            <div>${champ.winCount}승 ${champ.loseCount}패</div>
                            <div class="champ-win-rate">${champ.winRate}%</div>
                            <div>${champ.kda}</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- end of detail win rate wrapper -->
        </div>
        <div id="game-result-review-wrapper">
            <div id="game-result-wrapper">
                <c:forEach var="match" items="last20Matches">
                    <div class="each-game-result-wrapper">
                        <img src="/assets/img/user-history/champions/${match.championName}.jpg" alt="플레이한 챔피언 아이콘"
                             class="playing-champ-img">
                        <div class="rune-spell-wrapper">
                            <div class="rune-wrapper">
                                <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                                <img src="/assets/img/user-history/rune/sub/${}.png" alt="보조 룬">
                            </div>
                            <div class="spell-wrapper">
                                <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                                <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                            </div>
                        </div>
                        <!-- end of rune and spell wrapper -->
                        <div class="each-kda-item-wrapper">
                            <div class="each-kda-wrapper">
                                <div class="each-kda">${} / ${} / ${}</div>
                                <div class="each-avg-kda">${} : 1 KDA</div>
                            </div>
                            <div class="each-item-wrapper">
                                <!-- 구매한 아이템만 출력 -->
                                <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                                <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                                <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                                <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                                <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <!-- 승리시 색상 -->
                <div class="each-game-result-wrapper" style="background: #BCDBFF;">
                    <img src="/assets/img/user-history/champions/RekSai_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
                <div class="each-game-result-wrapper">
                    <img src="/assets/img/user-history/champions/Leona_0.jpg" alt="플레이한 챔피언 아이콘"
                         class="playing-champ-img">
                    <div class="rune-spell-wrapper">
                        <div class="rune-wrapper">
                            <img src="/assets/img/user-history/rune/main/VeteranAftershock.png" alt="주 룬">
                            <img src="/assets/img/user-history/rune/sub/7203_Whimsy.png" alt="보조 룬">
                        </div>
                        <div class="spell-wrapper">
                            <img src="/assets/img/user-history/spell/SummonerFlash.png" alt="소환사 주문 1">
                            <img src="/assets/img/user-history/spell/SummonerDot.png" alt="소환사 주문 2">
                        </div>
                    </div>
                    <!-- end of rune and spell wrapper -->
                    <div class="each-kda-item-wrapper">
                        <div class="each-kda-wrapper">
                            <div class="each-kda">${} / ${} / ${}</div>
                            <div class="each-avg-kda">${} : 1 KDA</div>
                        </div>
                        <div class="each-item-wrapper">
                            <!-- 구매한 아이템만 출력 -->
                            <img src="/assets/img/user-history/item/2422.png" alt="아이템 1">
                            <img src="/assets/img/user-history/item/3031.png" alt="아이템 2">
                            <img src="/assets/img/user-history/item/3072.png" alt="아이템 3">
                            <img src="/assets/img/user-history/item/3094.png" alt="아이템 4">
                            <img src="/assets/img/user-history/item/3364.png" alt="아이템 5">
                        </div>
                    </div>
                </div>
                <!-- end of each game wrapper -->
            </div>
            <!-- end of game result wrapper -->
            <section id="review-section">
                <h2>후기</h2>
                <div id="review-wrapper">
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                    <div class="each-review-wrapper">
                        <div class="review-profile-img"></div>
                        <div class="review-user-nickname-comment-wrapper">
                            <div class="review-user-nickname">${}</div>
                            <div class="review-comment">${}</div>
                        </div>
                        <div class="review-rate-wrapper">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                            <img src="/assets/img/main/star.png" alt="별점">
                        </div>
                    </div>
                    <!-- end of each review wrapper -->
                </div>
            </section>
            <!-- end of review section -->
        </div>
        <!-- end of game result and review wrapper-->
    </div>
    <!--    end of history wrapper-->
</div>

</body>

</html>