package site.woulduduo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Message;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.service.UserService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @BeforeEach
    void makeChattingTest(){

        List<User> users = userRepository.findAll();

        Chatting chatting = Chatting.builder()
                .chattingFrom(users.get(1))
                .chattingTo(users.get(2))
                .build();
        chattingRepository.save(chatting);
    }

//    @BeforeEach
//    void insertDummyMessages(){
//
//        List<User> users = userRepository.findAll();
//
//        Message.builder()
//                .user(users.get(1))
//                .messageContent("테스트지롱")
//                .chatting()
//                .build();
//    }

}