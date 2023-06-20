package site.woulduduo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.chatting.MessageRequestDTO;
import site.woulduduo.dto.response.chatting.ChattingDetailResponseDTO;
import site.woulduduo.dto.response.chatting.ChattingListResponseDTO;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.User;
import site.woulduduo.repository.UserRepository;
import site.woulduduo.service.ChattingService;
import site.woulduduo.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ChattingController {

    private final ChattingService chattingService;
    private final MessageService messageService;
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

//      대화 읽음 처리 해야함**
        messageService.readMessage(userId, chattingNo);

        ChattingDetailResponseDTO messages = chattingService.getChattingDetail(userId, chattingNo);
        return ResponseEntity.ok().body(messages);
    }

    //메세지 저장하기
    @PostMapping("/messages")
    public ResponseEntity<?> sendMessage(
            //            HttpSession session
            @RequestBody MessageRequestDTO dto
    ){
        boolean flag = messageService.saveMessage(dto);
        return ResponseEntity.ok().body(flag);
    }

    //안읽은 메세지 개수 읽어오기
    // 추후 session에 회원정보 담기면 경로 수정
    @GetMapping("/messages/unread/{userId}/{chattingNo}")
    public ResponseEntity<?> getUnreadMessageCount(
            //            HttpSession session
            @PathVariable String userId
            ,@PathVariable long chattingNo
    ){
        User user = userRepository.findByUserAccount(userId);
        Chatting chatting = chattingService.findByChattingNo(chattingNo);
        int unreadMessages = chattingService.countUnreadMessages(chatting, user);

        return ResponseEntity.ok().body(unreadMessages);
    }


}