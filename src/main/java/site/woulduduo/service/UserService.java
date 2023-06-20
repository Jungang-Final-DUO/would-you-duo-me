package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.user.*;
import site.woulduduo.dto.riot.LeagueV4DTO;
import site.woulduduo.dto.riot.MatchV5DTO;
import site.woulduduo.dto.riot.MostChampInfo;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;
import site.woulduduo.entity.UserProfile;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.exception.NoRankException;
import site.woulduduo.repository.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserQueryDSLRepositoryCustom userQueryDSLRepositoryCustom;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final AccuseRepository accuseRepository;
    private final ReplyRepository replyRepository;
    private final PasswordEncoder passwordEncoder;
    private final RiotApiService riotApiService;
    private final MatchingRepository matchingRepository;
    private final FollowRepository followRepository;
    private final UserProfileRepository userProfileRepository;
    private final MostChampRepository mostChampRepository;
    private final MatchingService matchingService;


    final String id = "abc1234";

    public void register(UserRegisterRequestDTO dto) {

        // 이메일 중복 검사
        if (userRepository.countByUserEmail(dto.getUserEmail()) > 0) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        // 닉네임 중복 검사
        if (userRepository.countByUserNickname(dto.getUserNickname()) > 0) {
            throw new IllegalArgumentException("이미 등록된 닉네임입니다.");
        }

        // 소환사 아이디 중복 검사
        if (userRepository.countByLolNickname(dto.getLolNickname()) > 0) {
            throw new IllegalArgumentException("이미 등록된 롤 닉네임입니다.");
        }

        // 회원 정보 저장

        User user = User.builder()
                .userAccount(dto.getUserEmail())
                .userNickname(dto.getUserNickname())
                .userPassword(passwordEncoder.encode(dto.getUserPassword()))
                .userBirthday(dto.getUserBirthday())
                .userInstagram(dto.getUserInstagram().isEmpty() ? null : dto.getUserInstagram())
                .userTwitter(dto.getUserTwitter().isEmpty() ? null : dto.getUserTwitter())
                .userFacebook(dto.getUserFacebook().isEmpty() ? null : dto.getUserFacebook())
                .lolNickname(dto.getLolNickname())
                .userGender(dto.getUserGender() == Gender.M ? Gender.M : Gender.F)
                .lolTier(riotApiService.getTier(dto.getLolNickname()))
                .userRecentLoginDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

        // 프로필 사진 저장
        String[] profileImagePaths = dto.getProfileImagePaths();
        if (profileImagePaths != null) {
            for (String imagePath : profileImagePaths) {
                UserProfile userProfile = UserProfile.builder()
                        .user(user)
                        .profileImage(imagePath)
                        .build();
                userProfileRepository.save(userProfile);
            }
        }


        log.info("회원 가입이 완료되었습니다.");
    }

    // 중복검사 서비스 처리
    public int checkSignUpValue(String type, String keyword) {
        int flagNum;

        switch (type) {
            case "email":
                flagNum = userRepository.countByUserEmail(keyword);
                break;
            case "nickname":
                flagNum = userRepository.countByUserNickname(keyword);
                break;
            case "lolNickname":
                flagNum = userRepository.countByLolNickname(keyword);
                break;
            default:
                throw new IllegalArgumentException("잘못된 검사 타입입니다.");
        }

        return flagNum;
    }


//    public List<ProfileDetailResponseDTO> getUserProfileImage(String userAccount) {
//        log.info("userAccount: {}", userAccount);
//        // 예시: 임시로 빈 리스트를 반환
//        return new ArrayList<>();
//    }
//    public List<ProfileDetailResponseDTO> addProfile(ProfileAddRequestDTO dto) {
//        // addProfile 메서드의 구현 내용을 추가하면 됩니다.
//        // ...
//
//        // getUserProfileImage 서비스 메서드 호출
//        List<ProfileDetailResponseDTO> profileDetails = getUserProfileImage();
//        return profileDetails;
//    }
//
//
//    public List<ProfileDetailResponseDTO> getUserProfileImage(ProfileDeleteRequestDTO dto) {
//
//    }


    public boolean registerDUO(/*HttpSession session, */UserCommentRequestDTO dto) {

        User exUser = User.builder()
                .userSessionId("abc1234@ddd.com")
                .userAccount("abc1234@ddd.com")
                .userPassword("12345678")
                .lolTier(Tier.CHA)
                .userGender(Gender.M)
                .userBirthday(LocalDate.of(2000, 03, 16))
                .userNickname("HongChaa")
                .lolNickname("HongChaa")
                .build();

        userRepository.save(exUser);

        Optional<User> user = userRepository.findById(exUser.getUserSessionId());

        System.out.println("user = " + user);
        if (user.isEmpty()) {
            return false;
        }
        user.ifPresent(u -> {
            u.setUserPosition(dto.getUserPosition());
            u.setUserComment(dto.getUserComment());
            u.setUserMatchingPoint(dto.getUserMatchingPoint());

            userRepository.save(u);
        });
        return true;
    }

//    public ListResponseDTO<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type) {
//        userRepository.count();
//        return null;
//    }

    public List<UserByAdminResponseDTO> getUserListByAdmin() {


//        // Pageable객체 생성
//        Pageable pageable = PageRequest.of(
//                type.getPage() - 1,
//                type.getSize(),
//                Sort.by("createDate").descending()
//        );

        //전체불러오기
        List<User> all = userRepository.findAll();
        //user정보
//        List<User> users = all.getContent();

        //dto리스트생성 및 dto 생성
        List<UserByAdminResponseDTO> userListByAdmin = new ArrayList<>();
        UserByAdminResponseDTO dto = new UserByAdminResponseDTO();
        for (User user : all) {
            //bc,rc,rc,fc 카운터 찾는 메서드
            long accuseCount = accuseRepository.countByUser(user);
            long boardCount = boardRepository.countByUser(user);
            long replyCount = replyRepository.countByUser(user);
//            long followToCount = followRepository.findToByAccount(user);


            dto.setUserAccount(user.getUserAccount());
            dto.setGender(user.getUserGender().toString());
            dto.setBoardCount(boardCount);
            dto.setReplyCount(replyCount);
            dto.setReportCount(accuseCount);
            dto.setPoint(user.getUserCurrentPoint());
            dto.setFollowCount(3);

            userListByAdmin.add(dto);
        }
        List<UserByAdminResponseDTO> userListByAdmin1 = userListByAdmin;
        System.out.println("userListByAdmin1 = " + userListByAdmin1);

        return userListByAdmin;
    }

    public Map<String, Integer> countByAdmin() {
        Map<String, Integer> adminCount = new HashMap<>();
        int userFindAllCount = userFindAllCount();
        int userFindByToday = userFindByToday();
        int accuseFindAllCount = accuseFindAllCount();
        int accuseFindByToday = accuseFindByToday();
        int boardFindAllCount = boardFindAllCount();
        int boardFindByToday = boardFindByToday();

        adminCount.put("ua", userFindAllCount);
        adminCount.put("ut", userFindByToday);
        adminCount.put("aa", accuseFindAllCount);
        adminCount.put("at", accuseFindByToday);
        adminCount.put("ba", boardFindAllCount);
        adminCount.put("bt", boardFindByToday);

        return adminCount;

    }

    //전체 user 조회수(admin)
    public int userFindAllCount() {
        List<User> all = userRepository.findAll();
        int userSize = all.size();
        return userSize;
    }

    //오늘 가입한 회원 수(admin)
    public int userFindByToday() {
        int allWithJoinDate = userRepository.findAllWithJoinDate(LocalDate.now());
        return allWithJoinDate;
    }

    //전체 accuse 조회수(admin)
    public int accuseFindAllCount() {
        List<Accuse> all = accuseRepository.findAll();
        int accuseSize = all.size();
        return accuseSize;
    }

    //오늘 accuse 조회수(admin)
    public int accuseFindByToday() {
        int allWithAccuseWrittenDate = accuseRepository.findAllWithAccuseWrittenDate();
        return allWithAccuseWrittenDate;
    }

    //전체 게시글 조회수(admin)
    public int boardFindAllCount() {
        List<Board> all = boardRepository.findAll();
        int boardsize = all.size();
        return boardsize;
    }

    //오늘 작성된 게시글 조회수(admin)
    public int boardFindByToday() {
        int allWithJoinDate = boardRepository.findAllWithBoardWrittenDate();
        return allWithJoinDate;
    }


    //유저리스트 DTO변환(Admin)
    public List<UserByAdminResponseDTO> getUserListByAdmin(AdminSearchType type) {


//        // Pageable객체 생성
//        Pageable pageable = PageRequest.of(
//                type.getPage() - 1,
//                type.getSize(),
//                Sort.by("createDate").descending()
//        );

        //user정보
//        List<User> users = all.getContent();

        //전체불러오기
        List<User> all = userRepository.findAll();

        //dto리스트생성 및 dto 생성
        List<UserByAdminResponseDTO> userListByAdmin = new ArrayList<>();
        int i = 1;
        for (User user : all) {
            UserByAdminResponseDTO dto = new UserByAdminResponseDTO();

            //bc,rc,rc,fc 카운터 찾는 메서드
            long accuseCount = accuseRepository.countByUser(user);
            long boardCount = boardRepository.countByUser(user);
            long replyCount = replyRepository.countByUser(user);
            long followCount = followRepository.findAllWithFollowTo(user.getUserAccount());

            dto.setRowNum(i);
            dto.setUserAccount(user.getUserAccount());
            dto.setGender(user.getUserGender().toString());
            dto.setBoardCount(boardCount);
            dto.setReplyCount(replyCount);
            dto.setReportCount(accuseCount);
            dto.setPoint(user.getUserCurrentPoint());
            dto.setFollowCount(followCount);
            i++;

            userListByAdmin.add(dto);
            List<UserByAdminResponseDTO> userListByAdmin1 = userListByAdmin;
        }
        List<UserByAdminResponseDTO> userListByAdmin2 = userListByAdmin;

        return userListByAdmin;
    }

//    public UserDetailByAdminResponseDTO getUserDetailByAdmin(String userAccount){
//
//        return null;
//    }
//
//    public boolean increaseUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }
//
//    public boolean changeUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }

    /**
     * 사용자의 듀오 정보를 구하는 메서드
     *
     * @param session     - 접속한 사용자
     * @param userAccount - 대상 사용자
     * @return - 응답 DTO
     */
    public UserHistoryResponseDTO getUserHistoryInfo(HttpSession session, String userAccount) {

        User foundUser = userRepository.findById(userAccount).orElseThrow(
                () -> new RuntimeException("해당하는 유저가 없습니다.")
        );
        String lolNickname = foundUser.getLolNickname();

        List<MatchV5DTO.MatchInfo.ParticipantDTO> last20ParticipantDTOList = riotApiService.getLast20ParticipantDTOList(lolNickname);

        LeagueV4DTO rankInfo = null;
        try {
            rankInfo = riotApiService.getRankInfo(lolNickname, "RANKED_SOLO_5x5");
        } catch (NoRankException e) {
            try {
                rankInfo = riotApiService.getRankInfo(lolNickname, "RANKED_FLEX_SR");
            } catch (NoRankException ex) {
                rankInfo = LeagueV4DTO.builder().build();
            }
        }

        // 사용자가 받은 모든 리뷰
        List<UserReviewResponseDTO> reviews = foundUser.getChattingFromList().stream()
                .map(c -> c.getMatchingList().stream()
                        .map(UserReviewResponseDTO::new)
                        .collect(toList())
                ).collect(toList())
                .stream()
                .flatMap(List::stream)
                .collect(toList());

        boolean isFollowed = false;
        try {
            isFollowed = followRepository.existsByFollowFromAndFollowTo(session.getAttribute("로그인키").toString(), userAccount);
        } catch (NullPointerException ignored) {
        }

        List<MostChampInfo> mostChampInfoList = riotApiService.getMost3Champions(lolNickname).stream()
                .map(m -> {
                    List<MatchV5DTO.MatchInfo.ParticipantDTO> championMatchInfoList = last20ParticipantDTOList.stream()
                            .filter(p -> p.getChampionName().equals(m))
                            .collect(toList());

                    int winCount = (int) championMatchInfoList.stream()
                            .filter(MatchV5DTO.MatchInfo.ParticipantDTO::isWin).count();

                    int loseCount = (int) championMatchInfoList.stream()
                            .filter(c -> !c.isWin()).count();

                    int winRate = winCount * 100 / (winCount + loseCount);

                    int kills = championMatchInfoList.stream()
                            .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getKills).sum();

                    int deaths = championMatchInfoList.stream()
                            .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getDeaths).sum();

                    int assists = championMatchInfoList.stream()
                            .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getAssists).sum();

                    double kda = (double) (kills + assists) / deaths;

                    return MostChampInfo.builder()
                            .champName(m)
                            .winCount(winCount)
                            .loseCount(loseCount)
                            .winRate(winRate)
                            .kda(kda)
                            .build();
                })
                .collect(toList());

        return UserHistoryResponseDTO.builder()
                .userAccount(userAccount)
                .profileImage(foundUser.getLatestProfileImage())
                .userNickname(foundUser.getUserNickname())
                .userPosition(foundUser.getUserPosition())
                .isFollowed(isFollowed)
                .userAvgRate(String.format("%.2f", foundUser.getUserAvgRate()))
                .userMatchingPoint(foundUser.getUserMatchingPoint())
                .userInstagram(foundUser.getUserInstagram())
                .userFacebook(foundUser.getUserFacebook())
                .userTwitter(foundUser.getUserTwitter())
                .lolNickname(lolNickname)
                .userComment(foundUser.getUserComment())
                .tier(foundUser.getLolTier())
                .userReviews(reviews)
                // 모스트 3 챔피언 정보
                .mostChampInfos(mostChampInfoList)
                // riot api 를 통해 얻어오는 솔로랭크 혹은 자유랭크 데이터
                .leaguePoints(rankInfo.getLeaguePoints())
                .totalWinCount(rankInfo.getWins())
                .totalLoseCount(rankInfo.getLosses())
                .winRate(Math.round(rankInfo.getWinRate() * 100))
                // 최근 20 매치의 정보 데이터
                .last20Matches(last20ParticipantDTOList.stream()
                        .map(MatchResponseDTO::new)
                        .collect(toList()))
                .userReviews(matchingService.getGottenReview(userAccount, 1).getList())
                .build();

    }

//    public UserDetailByAdminResponseDTO getUserDetailByAdmin(String userAccount){
//
//        return null;
//    }
//
//    public boolean increaseUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }
//
//    public boolean changeUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }

    public List<UserProfilesResponseDTO> getUserProfileList(/*HttpSession session, */UserSearchType userSearchType) {
        List<UserProfilesResponseDTO> userProfileList = userQueryDSLRepositoryCustom.getUserProfileList(userSearchType);

        for (UserProfilesResponseDTO userProfile : userProfileList) {
            log.info("@@@ userProfile @@@@@ : {}", userProfile.toString());
        }
        return userProfileList;
    }


}
