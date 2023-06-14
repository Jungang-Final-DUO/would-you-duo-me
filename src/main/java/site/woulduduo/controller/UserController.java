package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;

@Controller
@XSlf4j
@RequiredArgsConstructor
public class UserController {

    // 회원 가입 양식 요청
    @GetMapping("/user/sign-up")
    public String signUp() {
//        log.info("/user/sign-up GET ");
        return "user/sign-up";
    }

    //회원가입 처리 요청
    @PostMapping("/user/sign-up")
    public String signUp(UserRegisterRequestDTO dto) {
//        log.info("/user/sign-up POST! ");
        return "redirect:/user/sign-in";
    }


}
