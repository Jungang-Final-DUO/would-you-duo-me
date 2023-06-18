package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import site.woulduduo.dto.request.chatting.MatchingFixRequestDTO;
import site.woulduduo.dto.request.chatting.MatchingMakeRequestDTO;
import site.woulduduo.entity.Matching;
import site.woulduduo.enumeration.MatchingStatus;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MatchingRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MatchingServiceTest {

    @Autowired
    private MatchingService matchingService;
    @Autowired
    MatchingRepository matchingRepository;
    @Autowired
    private ChattingRepository chattingRepository;

    //    매칭 신청하기
//    6/18 테스트 완료
    @Test
    @DisplayName("매칭을 신청하면 매칭 번호를 리턴받는다")
    void makeMatchingTest() {
        MatchingMakeRequestDTO dto = MatchingMakeRequestDTO.builder().chattingNo(2L).build();

        long matchingNo = matchingService.makeMatching(dto);

        System.out.println("matchingNo = " + matchingNo);
    }

    //    매칭 거절하기
//     6/18 테스트 완료
    @Test
    @DisplayName("매칭을 거절하면 매칭상태를 REJECT로 변경한다")
    void rejectMatchingTest() {
        long matchingNo = 2L;
        boolean flag = matchingService.rejectMatching(matchingNo);
        Matching rejected = matchingRepository.findByMatchingNo(matchingNo);
        assertEquals(MatchingStatus.REJECT, rejected.getMatchingStatus());
    }

    //    매칭 확정하기
//  6/18 테스트 완료
    @Test
    @DisplayName("매칭을 확정하면 매칭일자가 입력되고 상태를 CONFIRM으로 변경한다")
    void fixScheduleTest() {
        MatchingFixRequestDTO dto = MatchingFixRequestDTO.builder()
                .matchingNo(3L)
                .matchingDate(LocalDate.now())
                .build();
        boolean flag = matchingService.fixSchedule(dto);
        Matching rejected = matchingRepository.findByMatchingNo(dto.getMatchingNo());
        assertEquals(MatchingStatus.CONFIRM, rejected.getMatchingStatus());
    }

}