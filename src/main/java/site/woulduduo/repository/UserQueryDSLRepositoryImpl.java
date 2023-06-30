package site.woulduduo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.response.user.UserProfileResponseDTO;
import site.woulduduo.entity.QMostChamp;
import site.woulduduo.entity.QUser;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserQueryDSLRepositoryImpl implements UserQueryDSLRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUser user = QUser.user;
    private final QMostChamp mostChamp = QMostChamp.mostChamp;

    @Override
    public List<UserProfileResponseDTO> getUserProfileList(UserSearchType userSearchType) {
        System.out.println("IMPLPosition = " + userSearchType.getPosition());
        System.out.println("IMPLKeyword = " + userSearchType.getKeyword());
        List<User> userList = queryFactory.selectFrom(user)
                .join(user.mostChampList, mostChamp).fetchJoin()
                .where(keywordContains(userSearchType.getKeyword())
                        , positioneq(userSearchType.getPosition())
                        , gendereq(userSearchType.getGender())
                        , tiereq(userSearchType.getTier())
                        , user.userMatchingPoint.isNotNull()
                )
                .offset(checkPage(userSearchType.getPage()))
                .limit(userSearchType.getSize())
//                .orderBy(user.userAvgRate.desc())
                .fetch();
        log.info("### userList ###: {}", userList);
        // select 로 불러온 user 리스트 UserProfilesResponseDTO로 변환해 리스트에 담아주기
        List<UserProfileResponseDTO> userProfiles = new ArrayList<>();
        for (User user : userList) {
            UserProfileResponseDTO dto = UserProfileResponseDTO.builder()
                    .userAccount(user.getUserAccount())
                    .userGender(user.getUserGender())
                    .userComment(user.getUserComment())
                    .userMatchingPoint(user.getUserMatchingPoint())
                    .tier(String.valueOf(user.getLolTier()))
                    .userInstagram(user.getUserInstagram())
                    .userFacebook(user.getUserFacebook())
                    .userTwitter(user.getUserTwitter())
                    .userPosition(user.getUserPosition())
                    .userNickname(user.getUserNickname())
                    .avgRate(user.getUserAvgRate())
                    .mostChampList(user.getMostChampList())
                    .profileImage((user.getUserProfileList().size() == 0) ? "basic" : user.getUserProfileList().get(0).getProfileImage())
                    .build();

            userProfiles.add(dto);
        }


        return userProfiles;
    }

    @Override
    public int modifyProfileCard(UserCommentRequestDTO dto/*, HttpSession session*/) {


        return 0;
    }


    // 2페이지부터 offset 조정
    private Long checkPage(int page) {
        return page == 1 ? 0 : ((long) page - 1) * 20;
    }

    // 티어 파라미터가 null인지 체크
    private BooleanExpression tiereq(Tier tier) {
        return (tier != null) ? user.lolTier.eq(tier) : null;
    }


    // 성별 파라미터가 null인지 체크
    private BooleanExpression gendereq(Gender gender) {
        return (gender != null) ? user.userGender.eq(gender) : null;
    }

    // 포지션 파라미터가 null인지 체크
    private BooleanExpression positioneq(Position position) {
        return (position != null) ? user.userPosition.eq(position) : null;
    }

    // 검색 키워드가 null인지 체크
    private BooleanExpression keywordContains(String keyword) {
        return StringUtils.isNullOrEmpty(keyword) ? null : user.userNickname.contains(keyword);
    }


}