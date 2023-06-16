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
    ChattingRepository chattingRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("채팅 데이터 1개 생성")
    void makeChattingTest(){

        List<User> users = userRepository.findAll();

//        채팅 데이터를 생성하기 전에 이미 존재하는 채팅인지 검사
        List<Chatting> chattingsFrom = chattingRepository.findByChattingFromAndChattingTo(users.get(0), users.get(3));
        List<Chatting> chattingsTo = chattingRepository.findByChattingFromAndChattingTo(users.get(3), users.get(0));
        if(chattingsFrom.size() > 0 || chattingsTo.size() > 0){
            System.out.println("이미 존재하는 채팅내역임");
        } else {
//            존재하지 않으면 데이터 생성
            Chatting chatting = Chatting.builder()
                    .chattingFrom(users.get(0))
                    .chattingTo(users.get(3))
                    .build();
            Chatting saved = chattingRepository.save(chatting);
            System.out.println(saved);
        }
        assertEquals(5, chattingRepository.count());
    }
}