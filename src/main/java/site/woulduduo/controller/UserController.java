package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.woulduduo.dto.request.login.LoginRequestDTO;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.user.UserByAdminResponseDTO;
import site.woulduduo.dto.response.user.UserHistoryResponseDTO;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.LoginResult;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.UserRepository;
import site.woulduduo.service.EmailService;
import site.woulduduo.service.UserService;
import site.woulduduo.util.upload.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static site.woulduduo.enumeration.LoginResult.SUCCESS;
import static site.woulduduo.util.LoginUtil.isAutoLogin;
import static site.woulduduo.util.LoginUtil.isLogin;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.root-path}")
    private String rootPath;

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;

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
                String savePath = FileUtil.uploadFile(profileImage, rootPath);
                savePaths[i] = savePath;
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

    // 로그인 양식 요청
    @GetMapping("/user/sign-in")
    public String signIn(HttpServletRequest request) {
        log.info("/user/sign-in GET");
        String referer = request.getHeader("Referer");
        return "index";
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
        return "redirect:/user/sign-in";
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
        return "redirect:/user/sign-in";
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

    @GetMapping("/user/admin")
    //관리자 페이지 열기
    public String showAdminpage(/*HttpSession session, */Model model) {
        Map<String, Integer> countByAdmin = userService.countByAdmin();
        model.addAttribute("count", countByAdmin);
        countByAdmin.get("ua");
        return "admin/admin";
    }

    //관리자 페이지 리스트 가져오기
    public ResponseEntity<?> getUserListByAdmin(/*AdminSearchType type*/) {
        List<UserByAdminResponseDTO> userListByAdmin = userService.getUserListByAdmin();


        return ResponseEntity
                .ok()
                .body(userListByAdmin);
    }

//    @GetMapping("/user/detail/admin")
//    //관리자 페이지 자세히 보기
//    public String showDetailByAdmin(HttpSession session,Model model, String userAccount){
//
//        return "";
//    }
//
//    @GetMapping("/user/ban")
//    public String changeBanStatus(HttpSession session, String userAccount){
//
//        return "redirect";
//    }
//
//    @GetMapping("/user/duo")
//    public String showDetailUser(HttpSession session, String userAccount){
//
//        return "";
//    }

    @GetMapping("/api/v1/users/{page}/{keyword}/{size}/{position}/{gender}/{tier}/{sort}")
    public ResponseEntity<?> getUserProfileList(int page, String keyword, int size, Position position, Gender gender, Tier tier, String sort/*, HttpSession session*/) {
//        UserSearchType userSearchType = UserSearchType.builder()
//                .position(Position.MID)
//                .gender(Gender.M)
//                .tier(Tier.DIA)
//                .sort("avgRate")
//                .build();

        UserSearchType userSearchType = new UserSearchType();
        userSearchType.setPosition(position);
        userSearchType.setGender(gender);
        userSearchType.setTier(tier);
        userSearchType.setSort(sort);

        return ResponseEntity.ok().body(userService.getUserProfileList(userSearchType));
    }

        // 유저 전적 페이지 이동
        @GetMapping("/user/user-history")
        public String showUserHistory (HttpSession session, Model model, String userAccount){

            log.info("/user/history?userAccount={} GET", userAccount);

            UserHistoryResponseDTO dto = userService.getUserHistoryInfo(session, userAccount);

            model.addAttribute("history", dto);

            return "user/user-history";

        }
    }

