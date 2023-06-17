package site.woulduduo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.chatting.MessageListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Message;
import site.woulduduo.entity.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChattingRepository chattingRepository;

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

}