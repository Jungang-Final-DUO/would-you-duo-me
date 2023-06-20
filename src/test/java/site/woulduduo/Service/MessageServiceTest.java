package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.chatting.MessageListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Message;
import site.woulduduo.entity.User;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.MessageRepository;
import site.woulduduo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@Rollback(false)
class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChattingRepository chattingRepository;
    @Autowired
    private UserRepository userRepository;


    //메세지 저장하기
    // 6/17 테스트 완료
    @Test
    @DisplayName("메세지를 저장할 수 있다")
    //    메세지 저장하기
    void saveMessageTest(){
        Chatting chatting = chattingRepository.findByChattingNo(3L);
        User user = userRepository.findByUserAccount("test3");

        Message message = Message.builder()
                .chatting(chatting)
                .user(user)
                .messageContent("하이욤")
                .build();
        try {
            Message saved = messageRepository.save(message);
            System.out.println("saved = " + saved);
        } catch (Exception e) {
            System.out.println("메세지 저장 실패!");
        }

    }

    //    메세지 불러오기.
    //    6/17 테스트 완료
    @Test
    @DisplayName("메세지 내역을 불러올 수 있다. 메세지가 없다면 인삿말 메세지를 저장하고 불러온다")
    void getMessagesTest (){
        Chatting chatting = chattingRepository.findByChattingNo(5L);

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

        List<MessageListResponseDTO> result = messages.stream()
                .map(MessageListResponseDTO::new)
                .collect(Collectors.toList());

        System.out.println(result);
    }

}