package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Matching;
import site.woulduduo.entity.Point;
import site.woulduduo.entity.User;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MatchingRepository;
import site.woulduduo.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final MatchingRepository matchingRepository;
    private final ChattingRepository chattingRepository;
    private final UserRepository userRepository;

    public int giveMatchingPoint(long chattingNo, long matchingNo) {
        System.out.println("서비스 " +chattingNo);
        System.out.println("서비스 확인");
        Matching matching = matchingRepository.findByMatchingNo(matchingNo);
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);

        Point givingPoint = Point.builder()
                .user(chatting.getChattingTo())
                .pointAmount(matching.getMatchingPoint())
                .chatting(chatting)
                .matching(matching)
                .build();
        Point pointHistory = pointRepository.save(givingPoint);

        User user = userRepository.findByUserAccount(chatting.getChattingTo().getUserAccount());
        user.setUserCurrentPoint(user.getUserCurrentPoint() + givingPoint.getPointAmount());
        user.addPoint(pointHistory);

        return pointHistory.getPointAmount();
    }

    public boolean searchPointHistory(long matchingNo) {
        Matching matching = matchingRepository.findByMatchingNo(matchingNo);
        return pointRepository.existsByMatching(matching);
    }
}
