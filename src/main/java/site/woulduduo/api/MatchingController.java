package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.chatting.ReviewWriteRequestDTO;
import site.woulduduo.service.MatchingService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MatchingController {

    private final MatchingService matchingService;

    @RequestMapping(value = "/api/v1/matchings/reviews", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> writeReview(HttpSession session, @RequestBody ReviewWriteRequestDTO dto) {

//       세션에 로그인처리가 완료된 이후에 사용
//       String userAccount = session.getAttribute(LOGIN_KEY);

        String userAccount = "user1";

        try {
            matchingService.writeReview(userAccount, dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok().build();

    }


}
