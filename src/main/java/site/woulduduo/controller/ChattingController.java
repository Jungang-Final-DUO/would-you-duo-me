package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.woulduduo.service.ChattingService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ChattingController {

    private final ChattingService chattingService;

    //인덱스로 이동 테스트
    @GetMapping("/chat")
    public String test(){
        return "index";
    }



}
