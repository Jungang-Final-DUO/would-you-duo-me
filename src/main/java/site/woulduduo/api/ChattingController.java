package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.response.chatting.ChattingDetailResponseDTO;
import site.woulduduo.dto.response.chatting.ChattingListResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.repository.UserRepository;
import site.woulduduo.service.ChattingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ChattingController {

    private final ChattingService chattingService;
    private final UserRepository userRepository;

    //채팅방목록 불러오기
    // 추후 session에 회원정보 담기면 경로 수정
    @GetMapping("/chattings/{userId}")
    public ResponseEntity<?> getChattingList(
//            HttpSession session
            @PathVariable String userId
            ){
        User user = userRepository.findByUserAccount(userId);
        List<ChattingListResponseDTO> chattingList = chattingService.getChattingList(user);

        return ResponseEntity.ok().body(chattingList);
    }

    //채팅방 대화내역 가져오기
    // 추후 session에 회원정보 담기면 경로 수정
    @GetMapping("/messages/{userId}/{chattingNo}")
    public ResponseEntity<?> getChattingDetail(
//            HttpSession session
            @PathVariable String userId
            ,@PathVariable long chattingNo
    ){
        ChattingDetailResponseDTO messages = chattingService.getChattingDetail(userId, chattingNo);
        return ResponseEntity.ok().body(messages);
    }

}
