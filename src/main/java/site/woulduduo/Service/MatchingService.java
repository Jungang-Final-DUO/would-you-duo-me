package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.chatting.MatchingFixRequestDTO;
import site.woulduduo.dto.request.chatting.MatchingMakeRequestDTO;
import site.woulduduo.dto.request.chatting.ReviewWriteRequestDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Matching;
import site.woulduduo.enumeration.MatchingStatus;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MatchingRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final ChattingRepository chattingRepository;

    //    매칭 신청하기
    public long makeMatching(MatchingMakeRequestDTO dto) {
        Chatting chatting = chattingRepository.findByChattingNo(dto.getChattingNo());

        Matching matching = Matching.builder()
                .chatting(chatting)
                .matchingPoint(chatting.getChattingTo().getUserMatchingPoint())
                .matchingStatus(MatchingStatus.REQUEST)
                .build();
        Matching saved = matchingRepository.save(matching);

        return saved.getMatchingNo();
    }

    //    매칭 거절하기
    public boolean rejectMatching(long matchingNo) {
        Matching matching = matchingRepository.findByMatchingNo(matchingNo);
        matching.setMatchingStatus(MatchingStatus.REJECT);
        try {
            Matching saved = matchingRepository.save(matching);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    //    매칭 확정하기
    public boolean fixSchedule(MatchingFixRequestDTO dto) {
        Matching matching = matchingRepository.findByMatchingNo(dto.getMatchingNo());
        matching.setMatchingDate(dto.getMatchingDate());
        matching.setMatchingStatus(MatchingStatus.CONFIRM);
        try {
            Matching saved = matchingRepository.save(matching);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 리뷰 작성하기
     * @param userAccount - 리뷰를 쓰는 사람의 userAccount
     * @param dto - 리뷰 작성에 필요한 dto
     * @return - 리뷰 작성에 성공했는지 실패했는지 리턴
     */
    public void writeReview(final String userAccount, final ReviewWriteRequestDTO dto) {

        Matching foundMatching = matchingRepository.findById(dto.getMatchingNo())
                .orElseThrow(() -> new RuntimeException("해당하는 매칭 정보가 없습니다."));

        if (!foundMatching.getChatting().getChattingFrom().getUserAccount().equals(userAccount)) {
            throw new RuntimeException("유효한 사용자가 아닙니다.");
        }

        foundMatching.setMatchingReviewRate(dto.getReviewRate());
        foundMatching.setMatchingReviewContent(dto.getReviewContent());

        matchingRepository.save(foundMatching);
    }
}
