package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.chatting.GivePointRequestDTO;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    //매칭 신청하려는 사람 포인트가 충분한지 확인
    @ResponseBody
    @GetMapping("/api/v1/points/check/{chattingNo}")
    public ResponseEntity<?> checkCurrentPoint(@PathVariable long chattingNo){
//        System.out.println("checkCurrentPoint 컨트롤러 확인");
        int result = pointService.checkCurrentPoint(chattingNo);
//        System.out.println("포인트 계산 결과 : " + result );
        return ResponseEntity.ok().body(result);
    }

    //매칭 요청한 회원 포인트 차감하기
    @ResponseBody
    @PostMapping("/api/v1/points/pay")
    public ResponseEntity<?> payMatchingPoint(@RequestBody GivePointRequestDTO dto){
//        System.out.println("payMatchingPoint 컨트롤러 확인");
//        System.out.println("payMatchingPoint 컨트롤러" +dto.getChattingNo());
        int paidPoints = pointService.payMatchingPoint(dto.getChattingNo(), dto.getMatchingNo());
        return ResponseEntity.ok().body(paidPoints);
    }


    //매칭 요청받은 듀오에게 포인트 지급하기
    @ResponseBody
    @PostMapping("/api/v1/points/give")
    public ResponseEntity<?> giveMatchingPoint(@RequestBody GivePointRequestDTO dto){
        int givenPoints = pointService.giveMatchingPoint(dto.getChattingNo(), dto.getMatchingNo());
        return ResponseEntity.ok().body(givenPoints);
    }

    //포인트 지급여부 확인
    @ResponseBody
    @GetMapping("/api/v1/points/matching/{matchingNo}")
    public ResponseEntity<?> searchPointHistory(@PathVariable long matchingNo){
        boolean flag = pointService.searchPointHistory(matchingNo);
        return ResponseEntity.ok().body(flag);
    }
}
