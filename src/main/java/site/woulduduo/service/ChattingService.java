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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final MessageRepository messageRepository;
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

//        채팅 상대방 찾기
        User user = userRepository.findByUserAccount(userAccount);
//        채팅 가져오기
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);
        ChattingDetailResponseDTO dto = new ChattingDetailResponseDTO();

//      채팅상대 닉네임, 프로필 사진 구하기
//      추후 세션에서 꺼내온 아이디로 변경해야함
        if (chatting.getChattingFrom() == /*session.getAttribute()*/ user) {
            dto.setUserNickname(chatting.getChattingTo().getUserNickname());

//          우리 회원가입 할때 프로필 사진 널값 체크 할건가..?
            List<UserProfile> myProfileList = chatting.getChattingFrom().getUserProfileList();
            if(myProfileList.size() != 0){
                dto.setMyProfileImage(chatting.getChattingFrom().getUserProfileList().get(0).getProfileImage());
            }
            List<UserProfile> yourProfileList = chatting.getChattingTo().getUserProfileList();
            if(yourProfileList.size() != 0){
                dto.setMyProfileImage(chatting.getChattingTo().getUserProfileList().get(0).getProfileImage());
            }

        } else {
            dto.setUserNickname(chatting.getChattingFrom().getUserNickname());
            dto.setMyProfileImage(chatting.getChattingTo().getUserProfileList().get(0).getProfileImage());
            dto.setYourProfileImage(chatting.getChattingFrom().getUserProfileList().get(0).getProfileImage());
        }

        dto.setMessageList(getMessages(chatting));

        return dto;
    }

    private List<MessageListResponseDTO> getMessages (Chatting chatting){
        List<Message> messages = messageRepository.findByChatting(chatting);
        if(messages.size() == 0){
            Message message = Message.builder()
                    .user(chatting.getChattingTo())
                    .messageContent("안녕하세요, 대화를 신청해주셔서 감사합니다!")
                    .chatting(chatting)
                    .build();
            Message saved = messageRepository.save(message);
            messages.add(saved);
        }

        return messages.stream()
                .map(MessageListResponseDTO::new)
                .collect(Collectors.toList());
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
