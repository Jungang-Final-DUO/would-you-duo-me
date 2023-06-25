package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    //매칭 요청한 회원 포인트 차감하기

    //매칭 요청받은 듀오에게 포인트 지급하기
    @PostMapping("/api/v1/points/give")
    public ResponseEntity<?> giveMatchingPoint(@RequestBody long chattingNo, @RequestBody long matchingNo){
        int givenPoints = pointService.giveMatchingPoint(chattingNo, matchingNo);
        return ResponseEntity.ok().body(givenPoints);
    }

    //포인트 지급여부 확인
    @GetMapping("/api/v1/points/matching/{matchingNo}")
    public ResponseEntity<?> searchPointHistory(@PathVariable long matchingNo){
        boolean flag = pointService.searchPointHistory(matchingNo);
        return ResponseEntity.ok().body(flag);
    }
}
