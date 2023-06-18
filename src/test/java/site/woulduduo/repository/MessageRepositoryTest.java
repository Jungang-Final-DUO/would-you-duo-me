package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Message;
import site.woulduduo.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(false)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChattingRepository chattingRepository;

    // 메세지 저장하기
    // 6/17 테스트 완료
    @Test
    @DisplayName("메세지를 저장할 수 있다")
    void saveTest(){
        User user = userRepository.findByUserAccount("test1");
        Chatting chatting = chattingRepository.findByChattingNo(6L);

        Message message = Message.builder()
                .user(user)
                .messageContent("안녕하세요, 대화를 신청해주셔서 감사합니다!")
                .chatting(chatting)
                .build();
        Message saved = messageRepository.save(message);
        assertEquals(1, messageRepository.count());
    }

    //최신 메세지 검색하기
    //6/18 테스트완료
    @Test
    @DisplayName("메세지를 발신 역순으로 검색한다")
    void findRecentMessageTest(){
        long chattingNo = 3L;
        Chatting chatting = chattingRepository.findByChattingNo(chattingNo);
        List<Message> recentMessage = messageRepository.findByChattingOrderByMessageTimeDesc(chatting);

        System.out.println(recentMessage);
    }
}