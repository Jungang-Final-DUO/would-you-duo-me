package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.response.chatting.ChattingListResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.service.ChattingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ChattingController {

    private final ChattingService chattingService;

    //채팅방목록 불러오기
    @GetMapping("/chattings")
    public ResponseEntity<?> getChattingList(
//            HttpSession session
            User user
    ){
        List<ChattingListResponseDTO> chattingList = chattingService.getChattingList(user);

        return ResponseEntity.ok().body(chattingList);
    }



}
