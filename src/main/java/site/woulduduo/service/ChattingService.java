package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.chatting.ChattingListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.repository.ChattingRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;

    //채팅 목록 가져오기
    public ChattingListResponseDTO getChattingList(
            HttpSession session
    ) {
//        String userAccount = session.getAttribute("");
        String userAccount = "hoshino_ai@ai.com";
        List<Chatting> chattings = chattingRepository.findByChattingFromAndChattingTo(userAccount);
        if(chattings.size() != 0){

        }

    }
}
