//package site.woulduduo.controller;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import site.woulduduo.service.UserService;
//
//import javax.servlet.http.HttpSession;
//
//@AllArgsConstructor
//public class UserController {
//    final UserService userService;
//
//    @GetMapping("/user/admin")
//    //관리자 페이지 열기
//    public String showAdminpage(HttpSession session, Model model){
//
//        return "";
//    }
//
//    //관리자 페이지 리스트 가져오기
//    public ResponseEntity<?> getUserListByAdmin(PageDTO page, AdminViewType type){
//
//        return
//    }
//
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
//        return redirect;
//    }
//
//    @GetMapping("/user/duo")
//    public String showDetailUser(HttpSession session, String userAccount){
//
//        return "";
//    }
//}
