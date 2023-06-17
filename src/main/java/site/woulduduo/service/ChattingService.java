package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.chatting.ChattingDetailResponseDTO;
import site.woulduduo.dto.response.chatting.MessageListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Message;
import site.woulduduo.entity.User;
import site.woulduduo.entity.UserProfile;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MessageRepository;
import site.woulduduo.repository.UserProfileRepository;
import site.woulduduo.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final MessageService messageService;
    private final UserRepository userRepository;

    //채팅 신청하기
    public long makeChatting(
//            HttpSession session,
            String myName,
            String userAccount
    ) {

        long chattingNo;

//        추후 세션에서 꺼내온 아이디로 변경해야함
        User me = userRepository.findByUserAccount(myName);
        User chattingUser = userRepository.findByUserAccount(userAccount);

        // 채팅 데이터를 생성하기 전에 이미 존재하는 채팅인지 검사
        Chatting chattingFrom = chattingRepository.findByChattingFromAndChattingTo(/*session.getAttribute()*/ me, chattingUser);
        Chatting chattingTo = chattingRepository.findByChattingFromAndChattingTo(chattingUser, /*session.getAttribute()*/ me);
        if (chattingFrom != null || chattingTo != null) {
            System.out.println("이미 존재하는 채팅내역임");
            chattingNo = chattingFrom != null ? chattingFrom.getChattingNo() : chattingTo.getChattingNo();

        } else {
            // 존재하지 않으면 데이터 생성
            Chatting chatting = Chatting.builder()
                    .chattingFrom(/*session.getAttribute()*/ me)
                    .chattingTo(chattingUser)
                    .build();
            Chatting saved = chattingRepository.save(chatting);
            chattingNo = saved.getChattingNo();

        }

//      chattingNo 리턴 후 프론트에서 해당 번호로 채팅방 열어주기
        return chattingNo;
    }

    //채팅방 디테일 내역 가져오기
    public ChattingDetailResponseDTO getChattingDetail(/*session.getAttribute()*/ String userAccount, long chattingNo) {

//        채팅 상대방 찾기 - 테스트완
        User user = userRepository.findByUserAccount(userAccount);
//        채팅 가져오기
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);
        log.info("chattingNo = {}", chatting.getChattingNo());

        ChattingDetailResponseDTO chattingResponseDTO = new ChattingDetailResponseDTO();

//      대표 프로필 사진 구하기 - 테스트완
        String fromImage = getRepresentativeProfile(chatting.getChattingFrom());
        String toImage = getRepresentativeProfile(chatting.getChattingTo());

//      내가 채팅을 걸었을 때
        if (chatting.getChattingFrom() == /*session.getAttribute()*/ user) {

//          대화 상대 닉네임
            chattingResponseDTO.setUserNickname(chatting.getChattingTo().getUserNickname());

//          나와 대화 상대의 프로필사진
            chattingResponseDTO.setMyProfileImage(fromImage);
            chattingResponseDTO.setYourProfileImage(toImage);

//      내가 채팅을 받았을 때
        } else {
            chattingResponseDTO.setUserNickname(chatting.getChattingFrom().getUserNickname());
            chattingResponseDTO.setMyProfileImage(toImage);
            chattingResponseDTO.setYourProfileImage(fromImage);
        }

        chattingResponseDTO.setMessageList(messageService.getMessages(chatting));

        return chattingResponseDTO;
    }

//  대표 프로필 사진 가져오기
    public static String getRepresentativeProfile(User user) {
        List<UserProfile> profileList = null;
        try {
            profileList = user.getUserProfileList().stream()
                    .sorted(Comparator.comparing(UserProfile::getProfileNo).reversed())
                    .limit(1)
                    .collect(Collectors.toList());
            return profileList.get(0).getProfileImage();
        } catch (IndexOutOfBoundsException e) {
            return "프로필 사진이 존재하지 않습니다."; // default 이미지 경로로 변경예정
        }
    }

////    메세지 내역 가져오기
//    public List<MessageListResponseDTO> getMessages (Chatting chatting){
//        List<Message> messages = messageRepository.findByChatting(chatting);
//        if(messages.size() == 0){
//            Message message = Message.builder()
//                    .user(chatting.getChattingTo())
//                    .messageContent("안녕하세요, 대화를 신청해주셔서 감사합니다!")
//                    .chatting(chatting)
//                    .build();
//            Message saved = messageRepository.save(message);
//            messages.add(saved);
//        }
//
//        return messages.stream()
//                .map(MessageListResponseDTO::new)
//                .collect(Collectors.toList());
//    }

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