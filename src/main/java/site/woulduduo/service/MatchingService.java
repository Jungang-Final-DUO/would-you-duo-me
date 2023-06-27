package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.chatting.MatchingFixRequestDTO;
import site.woulduduo.dto.request.chatting.ReviewWriteRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.page.PageResponseDTO;
import site.woulduduo.dto.response.user.MyPageReviewResponseDTO;
import site.woulduduo.dto.response.user.UserReviewResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Matching;
import site.woulduduo.enumeration.MatchingStatus;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MatchingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final ChattingRepository chattingRepository;

    //    매칭 신청하기
    public long makeMatching(long chattingNo) {
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);

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
        matching.setMatchingDate(dto.getLocalDateType(dto.getMatchingDate()));
        matching.setMatchingStatus(MatchingStatus.CONFIRM);
        try {
            Matching saved = matchingRepository.save(matching);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    // 게임 완료
    public boolean gameOverMatching(long matchingNo) {
        Matching matching = matchingRepository.findByMatchingNo(matchingNo);
        matching.setMatchingStatus(MatchingStatus.DONE);
        try {
            Matching saved = matchingRepository.save(matching);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 리뷰 작성하기
     *
     * @param userAccount - 리뷰를 쓰는 사람의 userAccount
     * @param dto         - 리뷰 작성에 필요한 dto
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

    /**
     * 해당 유저가 받은 모든 리뷰를 리턴 in history page
     *
     * @param userAccount - 해당 유저의 계정 명
     * @param pageNo      - 페이지 번호
     * @return - 리뷰 목록
     */
    public ListResponseDTO<UserReviewResponseDTO, Matching> getGottenReview(
            final String userAccount,
            final int pageNo
    ) {

        PageRequest pageInfo = PageRequest.of(pageNo - 1,
                10,
                Sort.by("matchingReviewRate").descending()
        );

        Page<Matching> pageDTO = matchingRepository
                .findOneByChattingTo(userAccount, pageInfo);

        List<UserReviewResponseDTO> hasReviewMatchingList = pageDTO
                .getContent().stream()
                .map(UserReviewResponseDTO::new)
                .collect(Collectors.toList());

        return ListResponseDTO.<UserReviewResponseDTO, Matching>builder()
                .count(pageNo)
                .pageInfo(new PageResponseDTO<>(pageDTO))
                .list(hasReviewMatchingList)
                .build();
    }

    /**
     * 해당 유저가 받은 모든 리뷰를 받아오는 메서드 in mypage
     *
     * @param userAccount - 해당 유저의 계정명
     * @param pageNo      - 페이지 번호
     * @return - 리뷰 목록
     */
    public ListResponseDTO<MyPageReviewResponseDTO, Matching> getGottenReviewOnMyPage(
            final String userAccount,
            final int pageNo
    ) {

        PageRequest pageInfo = PageRequest.of(pageNo - 1,
                15,
                Sort.by("matchingDate").ascending()
        );

        Page<Matching> pageDTO = matchingRepository
                .findOneByChattingToOnMyPage(userAccount, pageInfo);

        List<MyPageReviewResponseDTO> reviewList = pageDTO.getContent().stream()
                .map(m -> {
                    log.info("matchingInfo : {}", m);
                    return new MyPageReviewResponseDTO(m, true);
//                    return new MyPageReviewResponseDTO();
                })
                .collect(Collectors.toList());

        return ListResponseDTO.<MyPageReviewResponseDTO, Matching>builder()
                .list(reviewList)
                .count(pageNo)
                .pageInfo(new PageResponseDTO<>(pageDTO))
                .build();
    }

    /**
     * 해당 유저가 쓴 모든 리뷰를 받아오는 메서드 in mypage
     *
     * @param userAccount - 해당 유저의 계정명
     * @param pageNo      - 페이지 번호
     * @return - 리뷰 목록
     */
    public ListResponseDTO<MyPageReviewResponseDTO, Matching> getWrittenReviewOnMyPage(
            final String userAccount,
            final int pageNo
    ) {

        PageRequest pageInfo = PageRequest.of(pageNo - 1,
                15,
                Sort.by("matchingDate").ascending()
        );

        Page<Matching> pageDTO = matchingRepository
                .findOneByChattingFromOnMyPage(userAccount, pageInfo);

        List<MyPageReviewResponseDTO> reviewList = pageDTO.getContent().stream()
                .map(m -> new MyPageReviewResponseDTO(m, false)
                ).collect(Collectors.toList());

        return ListResponseDTO.<MyPageReviewResponseDTO, Matching>builder()
                .count(pageNo)
                .pageInfo(new PageResponseDTO<>(pageDTO))
                .list(reviewList)
                .build();
    }
    public List<Matching> findMatchingByChatting(long chattingNo) {
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);
            return matchingRepository.findByChatting(chatting).stream()
                    .sorted(Comparator.comparing(Matching::getMatchingNo).reversed())
                    .limit(1)
                    .collect(Collectors.toList());

    }
}
