package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.request.board.BoardModifyRequestDTO;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.board.BoardsByAdminResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
import site.woulduduo.service.BoardService;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
//경로
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
        log.info("/board/write: {} DTO", dto);


//        // 세션에 user_account 담기
//        String userAccount = (String) session.getAttribute("user_account");
//        dto.setUserAccount(userAccount);


        // 유효성 검사 및 에러 처리
        if (dto.getBoardCategory() == null) {
            BoardCategory boardCategory = dto.getBoardCategory();
            if (boardCategory != null) {
                if (boardCategory.equals("FREE")) {
                    dto.setBoardCategory(BoardCategory.FREE);
                } else if (boardCategory.equals("ACCUSE")) {
                    dto.setBoardCategory(BoardCategory.ACCUSE);
                } else {
                    // 선택한 값에 따라 추가적인 처리가 필요한 경우
                    // 예: 기본값 설정, 에러 처리 등
                }
            } else {
                // 선택한 값이 없는 경우의 처리
            }
        }

        // 서비스 호출
        boolean isBoardWritten = boardService.writeBoard(dto);
        if (isBoardWritten) {
            return "redirect:/board/list";
        } else {
            // 게시글 쓰기 실패 시, 적절한 에러..?
            return "board/board-write";
        }
    }


    @GetMapping("/board/")
    public String showBoardPage() {
        return "board/board-write"; //  JSP경로  yml,build 확인후  패키지경로까지확인하기
    }


    //글쓰기 모달창
    //   @PostMapping("/board/writemoadl")

//    @PostMapping("/board/write/writeMod")
//    public String writeModal() {
//        return "/board/writeModal"; // JSP 파일의 이름을 리턴
//    }


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
        return "redirect/board/boardNo/" + boardNo;

    }


    //창여는거=> 비동기
    //경로문제
    @GetMapping("/board/list")
    public String showBoardList() {
        log.info("asdsad");
        return "board/community";
    }

    //관리자페이지 boardlist 가져오기
    @GetMapping("api/v1/boards/admin")
    public ResponseEntity<?> getBoardListByAdmin(PageDTO dto){
        ListResponseDTO<BoardsByAdminResponseDTO, User> boardListByAdmin = boardService.getBoardListByAdmin(dto);
        ListResponseDTO<BoardsByAdminResponseDTO, User> boardsByAdminResponseDTOUserListResponseDTO = boardService.todayBoardByAdmin(dto);


        return ResponseEntity
                .ok()
                .body(boardListByAdmin);
    }




}

