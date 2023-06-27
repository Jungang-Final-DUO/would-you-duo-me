package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.woulduduo.aws.S3Service;
import site.woulduduo.dto.request.login.LoginRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.*;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.LoginResult;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.UserRepository;
import site.woulduduo.service.EmailService;
import site.woulduduo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static site.woulduduo.enumeration.LoginResult.SUCCESS;
import static site.woulduduo.util.LoginUtil.isAutoLogin;
import static site.woulduduo.util.LoginUtil.isLogin;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

//    @Value("${file.upload.root-path}")
//    private String rootPath;

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    // 메인페이지 - 프로필 카드 불러오기(비동기)
    @GetMapping("/api/v1/users/{page}/{keyword}/{size}/{position}/{gender}/{tier}/{sort}")
    public ResponseEntity<?> getUserProfileList(@PathVariable int page, @PathVariable String keyword, @PathVariable int size
            , @PathVariable String position, @PathVariable String gender
            , @PathVariable String tier, @PathVariable String sort/*, HttpSession session*/) {


        System.out.println(keyword + position + gender + tier + sort);

        UserSearchType userSearchType = new UserSearchType();
        userSearchType.setPage(page);
        userSearchType.setSize(size);
        if (!keyword.equals("-")) {
            userSearchType.setKeyword(keyword);
        }
        if (!position.equals("all")) {
            userSearchType.setPosition(Position.valueOf(position));
        }
        if (!gender.equals("all")) {
            userSearchType.setGender(Gender.valueOf(gender));
        }
        if (!tier.equals("all")) {
            userSearchType.setTier(Tier.valueOf(tier));
        }
        if (!keyword.equals("all")) {
            userSearchType.setSort(sort);
        }
        List<UserProfileResponseDTO> userServiceUserProfileList = userService.getUserProfileList(userSearchType);
        System.out.println("userServiceUserProfileList = " + userServiceUserProfileList);

        return ResponseEntity.ok().body(userServiceUserProfileList);
    }

    // 회원 가입 양식 요청
    @GetMapping("/user/sign-up")
    public String signUp() {
        log.info("/user/sign-up GET ");
        return "user/sign-up";
    }

    // 회원가입 처리 요청
    @PostMapping("/user/sign-up")
    public String signUp(@Valid UserRegisterRequestDTO dto) {
        log.info("/user/sign-up POST! dto : {}", dto);

        // 프로필 사진 배열 저장 로직
        MultipartFile[] profileImages = dto.getProfileImages();
        String[] savePaths = new String[profileImages.length];

        for (int i = 0; i < profileImages.length; i++) {
            MultipartFile profileImage = profileImages[i];
            if (!profileImage.isEmpty()) {
                // 업로드된 파일을 실제 로컬 저장소에 업로드하는 로직
//                String savePath = FileUtil.uploadFile(profileImage, rootPath);
//                savePaths[i] = savePath;
                String fileName = UUID.randomUUID() + "_" + profileImage.getOriginalFilename();
                try {
                    savePaths[i] = s3Service.uploadToBucket(profileImage.getBytes(), fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // UserRegisterRequestDTO에 프로필 사진 경로 설정
        dto.setProfileImagePaths(savePaths);

        // UserRegisterRequestDTO를 UserService의 회원가입 메서드로 전달하여 저장
        userService.register(dto);

        return "redirect:/";
    }

    // 아이디(이메일), 닉네임, 소환사아이디 중복검사
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<Boolean> check(@RequestParam String type, @RequestParam String keyword) {
        boolean isDuplicate;
        switch (type) {
            case "email":
                isDuplicate = userRepository.countByUserEmail(keyword) > 0;
                break;
            case "nickname":
                isDuplicate = userRepository.countByUserNickname(keyword) > 0;
                break;
            case "lolNickname":
                isDuplicate = userRepository.countByLolNickname(keyword) > 0;
                break;
            default:
                throw new IllegalArgumentException("잘못된 검사 타입입니다.");
        }
        return ResponseEntity.ok(isDuplicate); // 중복되지 않은 경우에 true 반환
    }

    // 로그인 검증 요청
    @PostMapping("/user/sign-in")
    public String signIn(LoginRequestDTO dto
            , RedirectAttributes ra
            , HttpServletResponse response
            , HttpServletRequest request
    ) {

        log.info("/user/sign-in POST ! - {}", dto);

        LoginResult result = userService.authenticate(dto, request.getSession(), response);

        // 로그인 성공시
        if (result == SUCCESS) {

            // 서버에서 세션에 로그인 정보를 저장
            userService.maintainLoginState(
                    request.getSession(), dto.getUserAccount());

            return "redirect:/";
        }

        // 1회용으로 쓰고 버릴 데이터
        ra.addAttribute("msg", result);

        // 로그인 실패시
        return "redirect:/";
    }

    // 로그아웃 요청 처리
    @GetMapping("/user/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        // 로그인 중인지 확인
        if (isLogin(session)) {

            // 자동로그인 상태라면 해제
            if (isAutoLogin(request)) {
                userService.autoLoginClear(request, response);
            }

            // 세션에서 login정보 제거
            session.removeAttribute("login");

            // 세션 초기화
            session.invalidate();
            return "redirect:/";
        }
        return "redirect:/";
    }


//    // 프로필 사진 등록
//    @PostMapping("/api/v1/users/profile")
//    public ResponseEntity<?> addProfile(ProfileAddRequestDTO dto, HttpSession session) {
//        log.info("profileAddRequestDTO : {}", dto);
//        return ResponseEntity.ok().build();
//    }
//
//    // 프로필 사진 삭제
//    @DeleteMapping("/api/v1/users/profile")
//    public ResponseEntity<?> deleteProfile(ProfileDeleteRequestDTO dto, HttpSession session) {
//        log.info("profileDeleteRequestDTO : {}", dto);
//        return ResponseEntity.ok().build();
//    }


    // 마이페이지 - 프로필 카드 등록페이지 열기
    @GetMapping("/user/register-duo")
    public String registerDUO(/*HttpSession session, */Model model) {

        return "my-page/mypage-duoprofile";
    }

    // 마이페이지 - 프로필카드 등록 처리
    @PostMapping("/user/register-duo")
    public String registerDUO(/*HttpSession session, */UserCommentRequestDTO dto) {

        boolean b = userService.registerDUO(/*session, */dto);
        log.info("프로필카드등록 성공여부 : {}", b);
        log.info("@@@@dto@@@@ :{}", dto);

        return "redirect:/user/register-duo";
    }

    // 마이 페이지 - 쓴 리뷰 페이지 열기
    @GetMapping("/user/matching-list")
    public String showMatchingList(HttpSession session) {
        return "my-page/matching-list";
    }

    @GetMapping("/user/admin")
    //관리자 페이지 열기
    public String showAdminpage(/*HttpSession session, */Model model) {
        AdminPageResponseDTO adminPageInfo = userService.getAdminPageInfo();
        model.addAttribute("count", adminPageInfo);
        return "admin/admin";
    }

    @GetMapping("/user/modal")
//모달테스트
 public String modalTest(/*HttpSession session, */Model model) {

        return "admin/adminModal";
    }

    //관리자 페이지 리스트 가져오기
    @GetMapping("/api/v1/users/admin")
    public ResponseEntity<?> getUserListByAdmin(
             @ModelAttribute  PageDTO dto){
        log.info("{}ddttoo==",dto);
        ListResponseDTO<UserByAdminResponseDTO, User> userListByAdmin = userService.getUserListByAdmin(dto);
        log.info("userbyadmin11111 : {}",userListByAdmin);

        log.info("/api/v1/users/admin/");


        return ResponseEntity
                .ok()
                .body(userListByAdmin);
    }

    //관리자 페이지 금일 가입자 리스트 가져오기
    @GetMapping("/api/v1/users/admin1")
    public ResponseEntity<?> getTodayUserListByAdmin(
            PageDTO dto) {
        ListResponseDTO<UserByAdminResponseDTO, User> userListTodayByAdmin = userService.todayUserByAdMin(dto);
        log.info("userListTodayByAdmin123 : {}",userListTodayByAdmin);

   return ResponseEntity
           .ok()
           .body(userListTodayByAdmin);
    }

    @GetMapping("/user/detail/admin")
    //관리자 페이지 자세히 보기
    public String showDetailByAdmin(HttpSession session,Model model,@RequestParam String userAccount){
        log.info("{}nickname = ",userAccount);

        UserDetailByAdminResponseDTO userDetailByAdmin = userService.getUserDetailByAdmin(userAccount);

        log.info("{}userDetailByAdmin = ",userDetailByAdmin);

        model.addAttribute("udByAdmin",userDetailByAdmin);
        return "admin/admin_user";

    }

    @GetMapping("/user/detail/banBoolean")
//    //관리자 페이지 자세히 보기
    public ResponseEntity<?> showBanIsBoolean(HttpSession session, @RequestParam String nickname){
        log.info("{}nickname = ",nickname);

        boolean userDetailByAdmin = userService.getUserBanBooleanByAdmin(nickname);

        log.info("{}userDetailByAdmin = ",userDetailByAdmin);

        return ResponseEntity
                .ok()
                .body(userDetailByAdmin);

    }

    @PostMapping("/user/point")
    @ResponseBody
    public ResponseEntity<?> changePointStatus(
            HttpSession session, @RequestBody UserModifyRequestDTO dto){
        log.info("{}-----------------------",dto);
        boolean b = userService.increaseUserPoint(dto);
        log.info("{}---123123",b);
        int i = userService.currentPoint(dto);


        return ResponseEntity
                .ok()
                .body(i);
    }




    @PostMapping("/user/ban")
    @ResponseBody
    public ResponseEntity<?> changeBanStatus(HttpSession session, @RequestBody UserModifyRequestDTO dto) {
        String userNickname = dto.getUserNickname();
        log.info("{}123123123",userNickname);
        log.info("{}---------userIsBanneduserIsBanned---------", dto);
        boolean b = userService.changeBanStatus(dto);
        log.info("{}aaaaaaaaaaaaaaaaaaaa123123", b);

        return ResponseEntity.ok().body(b);
    }


    // 유저 전적 페이지 이동
    @GetMapping("/user/user-history")
    public String showUserHistory(HttpSession session, Model model, String userAccount) {

        log.info("/user/history?userAccount={} GET", userAccount);

        UserHistoryResponseDTO dto = userService.getUserHistoryInfo(session, userAccount);

        model.addAttribute("history", dto);

        return "user/user-history";

    }

}

