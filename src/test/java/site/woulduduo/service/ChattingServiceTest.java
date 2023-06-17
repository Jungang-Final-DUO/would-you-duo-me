package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.User;
import site.woulduduo.repository.ChattingRepository;
import site.woulduduo.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ChattingServiceTest {

    @Autowired
    ChattingService chattingService;
    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("채팅 데이터 1개 생성")
    void makeChattingTest(){

        String userAccount = "test123";
        String me = "test1";

        long chattingNo = chattingService.makeChatting(me, userAccount);

        assertEquals(6, chattingNo);

    }
}