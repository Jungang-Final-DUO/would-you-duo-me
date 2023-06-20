package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.AdminPageResponseDTO;
import site.woulduduo.dto.response.user.UserByAdminResponseDTO;
import site.woulduduo.dto.response.user.UserDetailByAdminResponseDTO;
import site.woulduduo.dto.response.user.UserHistoryResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입 양식 요청
    @GetMapping("/user/sign-up")
    public String signUp() {
        log.info("/user/sign-up GET ");
        return "user/sign-up";
    }

    // 회원가입 처리 요청
    @PostMapping("/user/sign-up")
    public String signUp(@Valid UserRegisterRequestDTO dto) {
        log.info("/user/sign-up POST! ");

        // UserRegisterRequestDTO를 UserService의 회원가입 메서드로 전달하여 저장
        userService.register(dto);

        return "redirect:/user/sign-in";
    }

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
        AdminPageResponseDTO adminPageInfo = userService.getAdminPageInfo();
        model.addAttribute("count", adminPageInfo);
        return "admin/admin";
    }

    //관리자 페이지 리스트 가져오기
    @GetMapping("/api/v1/users/admin")
    public ResponseEntity<?> getUserListByAdmin(
            @PathVariable PageDTO dto){

        ListResponseDTO<UserByAdminResponseDTO, User> userListByAdmin = userService.getUserListByAdmin(dto);

        log.info("userbyadmin : {}",userListByAdmin);

        log.info("/api/v1/users/admin/");


        return ResponseEntity
                .ok()
                .body(userListByAdmin);
    }


    @GetMapping("/user/detail/admin")
    //관리자 페이지 자세히 보기
    public String showDetailByAdmin(/*HttpSession session*/Model model, String userAccount){
        UserDetailByAdminResponseDTO userDetailByAdmin = userService.getUserDetailByAdmin(userAccount);

        model.addAttribute("udByAdmin",userDetailByAdmin);
        return "admin/admin_user";

    }

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

