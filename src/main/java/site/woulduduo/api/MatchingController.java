package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.chatting.MatchingFixRequestDTO;
import site.woulduduo.dto.request.chatting.ReviewWriteRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserReviewResponseDTO;
import site.woulduduo.entity.Matching;
import site.woulduduo.service.MatchingService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/matchings")
@RequiredArgsConstructor
@Slf4j
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/{chattingNo}")
    public ResponseEntity<?> getMatchingNo(@PathVariable long chattingNo){
        Long matchingNo = null;
        try {
            matchingNo = matchingService.findMatchingByChatting(chattingNo).get(0).getMatchingNo();
            return ResponseEntity.ok().body(matchingNo);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/reviews", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> writeReview(HttpSession session, @RequestBody ReviewWriteRequestDTO dto) {

//       세션에 로그인처리가 완료된 이후에 사용
//       String userAccount = session.getAttribute(LOGIN_KEY);

        String userAccount = "user1";

        try {
            matchingService.writeReview(userAccount, dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    /**
     * 유저가 받은 리뷰를 가져오는 메서드
     *
     * @param userAccount - 해당 유저
     * @return - 유저가 쓴 리뷰 목록을 담은 응답
     */
    @GetMapping("/reviews/{userAccount}/{pageNo}")
    public ResponseEntity<?> getWrittenReview(
            @PathVariable String userAccount,
            @PathVariable int pageNo
    ) {

        ListResponseDTO<UserReviewResponseDTO, Matching> responseDTO = null;
        try {
            responseDTO = matchingService.getGottenReview(userAccount, pageNo);
        } catch (RuntimeException e) {
            ResponseEntity.internalServerError().body("알 수 없는 에러가 발생했습니다");
        }

        return ResponseEntity.ok(responseDTO);
    }

    // 매칭 신청
    @PostMapping
    public ResponseEntity<?> makeMatching(@RequestBody long chattingNo){
        long matchingNo = matchingService.makeMatching(chattingNo);
        return ResponseEntity.ok().body(matchingNo);
    }

    //매칭 확정
    @RequestMapping(value = "/fix", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> fixSchedule(@RequestBody MatchingFixRequestDTO dto){
        boolean flag = matchingService.fixSchedule(dto);
        log.info("확정 직전!");
        return ResponseEntity.ok().body(flag);
    }

    //매칭 거절
    @RequestMapping(value = "/reject", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> rejectMatching(long matchingNo){
        boolean flag = matchingService.rejectMatching(matchingNo);
        return ResponseEntity.ok().body(flag);
    }

    //게임 완료
    @RequestMapping(value = "/done", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> gameOverMatching(@RequestBody long matchingNo){
        log.info("done 컨트롤러 진입");
        boolean flag = matchingService.gameOverMatching(matchingNo);
        log.info("DONE으로 변경!");
        return ResponseEntity.ok().body(flag);
    }
}
