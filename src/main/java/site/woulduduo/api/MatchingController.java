package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.service.MatchingService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MatchingController {

    private final MatchingService matchingService;

    @PostMapping("/chattings/{userAccount}")
    public ResponseEntity<?> makeChatting(HttpSession session, @PathVariable String userAccount) {
        return ResponseEntity.ok().build();
    }

}
