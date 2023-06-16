package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.chatting.ChattingListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.User;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final UserRepository userRepository;

    //채팅 신청하기
    public boolean makeChatting(
            HttpSession session,
            String userAccount
    ) {
        User chattingUser = null;

        Optional<User> user = userRepository.findById(userAccount);
        if (user.isPresent()) {
            chattingUser = user.get();
        }

        // 채팅 데이터를 생성하기 전에 이미 존재하는 채팅인지 검사
        List<Chatting> chattingsFrom = chattingRepository.findByChattingFromAndChattingTo(/*session.getAttribute()*/ chattingUser, chattingUser);
        List<Chatting> chattingsTo = chattingRepository.findByChattingFromAndChattingTo(chattingUser, /*session.getAttribute()*/ chattingUser);
        if (chattingsFrom.size() > 0 || chattingsTo.size() > 0) {
            System.out.println("이미 존재하는 채팅내역임");

            return false;

        } else {
            // 존재하지 않으면 데이터 생성
            Chatting chatting = Chatting.builder()
                    .chattingFrom(/*session.getAttribute()*/ chattingUser)
                    .chattingTo(chattingUser)
                    .build();
            Chatting saved = chattingRepository.save(chatting);

            return true;
        }
    }

    //채팅 목록 가져오기
///    public ChattingListResponseDTO getChattingList(
////            HttpSession session
//            User user
//    ) {
////        String userAccount = session.getAttribute("");
//        Optional<Chatting> chattings = chattingRepository.findByChattingFromAndChattingTo(user, user);
//
//
//    }
}
