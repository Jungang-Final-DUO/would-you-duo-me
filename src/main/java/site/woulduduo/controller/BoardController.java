package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.request.board.BoardModifyRequestDTO;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.board.BoardsByAdminResponseDTO;
import site.woulduduo.entity.Board;
import site.woulduduo.service.BoardService;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor

public class BoardController {

    private final BoardService boardService;

    //게시물 등록

    //test
//        @Parameters({
//                @Parameter()
//        })

    // 컨트롤러에서 서비스 값 로그 찍어줌
    @PostMapping("/board/write")
    public String writeBoard(HttpSession session, BoardWriteRequestDTO dto) {
        log.info("/board/write");
        return "redirect:/board";
    }


    // 게시물 수정

    //gt 보여주는거
    @GetMapping("/api/v1/boards/{boardNo}")
    public ResponseEntity<?> modifyBoard(HttpSession session, Long boardNo) {

        log.info("/api/v1/boards/{boardNo}");
        return ResponseEntity
                .ok()
                .body(boardNo);


    }

    //해당하는 boardNo로 리다이렉트
    //    동기로 서비스로 보내주기 위한
    @PostMapping("/board/modify")
    public String modifyBoard(HttpSession session, BoardModifyRequestDTO dto) {

        System.out.println("/board/modify : POST");
        Long boardNo = boardService.modifyBoard(dto);
//string 뒤에 우리가 뽑은거 활용
        return "redirect/board/boardNo/"+boardNo;

    }

    //관리자페이지 boardlist 가져오기
    @GetMapping("/api/v1/boards/admin")
    public ResponseEntity<?> getBoardListByAdmin(PageDTO dto ){
        log.info("{}ddttoo==",dto);

        ListResponseDTO<BoardsByAdminResponseDTO, Board> boardListByAdmin = boardService.getBoardListByAdmin(dto);
//        ListResponseDTO<BoardsByAdminResponseDTO, Board> boardsByAdminResponseDTOUserListResponseDTO = boardService.todayBoardByAdmin(dto);
        log.info("boardListByAdmin : {}",boardListByAdmin);


        return ResponseEntity
                .ok()
                .body(boardListByAdmin);
    }

    //보드 삭제
    @DeleteMapping("/api/v1/boards/admin/delete")
    public ResponseEntity<?>  deleteBoardByAdmin(long boardNo ){
        log.info("{}boardNo1212==",boardNo);

        boolean b1 = boardService.deleteBoard(boardNo);
        System.out.println("b1 = " + b1);

        return ResponseEntity
                .ok()
                .body(b1);

    }


    //금일 등록된 boardlist
    @GetMapping("/api/v1/boards/admin1")
    public ResponseEntity<?> getTodayBoardListByAdmin(PageDTO dto){
        log.info("{}ddttoo==",dto);

        ListResponseDTO<BoardsByAdminResponseDTO, Board> boardsByAdminResponseDTOUserListResponseDTO = boardService.todayBoardByAdmin(dto);
        log.info("boardListByAdmin : {}",boardsByAdminResponseDTOUserListResponseDTO);


        return ResponseEntity
                .ok()
                .body(boardsByAdminResponseDTOUserListResponseDTO);
    }



}

