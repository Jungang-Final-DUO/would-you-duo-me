package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import site.woulduduo.Service.BoardService;
import site.woulduduo.dto.request.board.BoardModifyRequestDTO;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/board") //경로

public class BoardController {
    private final BoardService boardService;

    //게시물 등록

    //test
//        @Parameters({
//                @Parameter()
//        })

    // 컨트롤러에서 서비스 값 로그 찍어줌
    @PostMapping("/board/write")
    public String writeBoard(HttpSession session, BoardWriteRequestDTO dto){
        log.info("/board/write");
        return "redirect:/board";
    }
    @GetMapping("/api/v1/boards/{boardNo}")
    public ResponseEntity<?> modifyBoard(HttpSession session,Long boardNo){

        log.info("/api/v1/boards/{boardNo}");
        return  ResponseEntity
                .ok()
                .body(boardNo);



    }

    //해당하는 boardNo로 리다이렉트
    @PostMapping("/board/modify")
    public String modifyBoard(HttpSession session, BoardModifyRequestDTO dto){
        log.info("body/modify");
        return "redrirect:/board/boardNo";

    }


}