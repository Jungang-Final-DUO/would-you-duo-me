package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.service.EmailService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/user/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody UserRegisterRequestDTO dto) {
        try {
            String authCode = emailService.sendEmail(dto.getUserEmail());
            return ResponseEntity.ok(authCode);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송에 실패했습니다.");
        }
    }


}


