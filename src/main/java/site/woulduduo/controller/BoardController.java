package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.board.BoardModifyRequestDTO;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.board.BoardResponseDTO;
import site.woulduduo.dto.response.board.BoardsByAdminResponseDTO;
import site.woulduduo.dto.response.login.LoginUserResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
import site.woulduduo.service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor



//경로
public class BoardController {

    private final BoardService boardService;

    //게시물 등록

    // 컨트롤러에서 서비스 값 로그 찍어줌
    @PostMapping("/board/write")
    public String writeBoard(HttpSession session, BoardWriteRequestDTO dto) {
        log.info("/board/write: {} DTO", dto);

        LoginUserResponseDTO loginUserInfo
                = (LoginUserResponseDTO) session.getAttribute("login");
        String userAccount = loginUserInfo.getUserAccount();

        log.info("{}---------- ", userAccount);

        dto.setUserAccount(userAccount);

//        String userAccount = (String) session.getAttribute("user_account");
//        log.info("/board/write {} Dto",dto);
//
//        // 게시글 작성자 정보 설정
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



    //불러오기
//+ getBoardList(page : int, keyword : String, boardCategory : BoardCategory, sort : String) : ResponseEntity<?> @GetMapping("/api/v1/boards/{page}/{keyword}/{boardCategory}/{sort}") 검색은 제목으로만



    @GetMapping("/api/v1/boards/{page}/{keyword}/{boardCategory}")
    public ResponseEntity<List<BoardResponseDTO>> getBoardList(

            // 이부분이  @RequsetParm 쓰면 프론트연결 x

            @PathVariable int page,

            @PathVariable String keyword,
            @PathVariable BoardCategory boardCategory

    ) {
        // TODO : request 를 받는 곳.
        // 1. controller 맵핑 -> 2. service 핵심 로직(키워드로 찾는다, 카테고리로 찾는다, 전체 조회를 한다.) ->
        // 3. repository 데이터 취득 -> 4. 취득한 데이터가 service 가 controller 리턴 ->
        // 5. 데이터들을 controller 가 client 리턴

        // TODO : error 상황 : 데이터가 안 넘어온다던가, 데이터가 이상한 데이터가 온다던가, 데이터가 없다거나, 못 받거나...
        // 이러한 문제들이 어느 시점에서 발생하는지 알 수가 없음.

        // ---------- debug TODO : 1번 맵핑에 대한 디버깅
        log.info("page :{}", page );
        log.info("boardCategory:{}", boardCategory);
        log.info("keyword:{}", keyword);
        // ---------- debug




        List<BoardResponseDTO> boardList = boardService.getBoardList(page, keyword, boardCategory);


        System.out.println("================123123=========================");
        System.out.println(boardList); // TODO : 4. 취득한 데이터가 service 가 controller 리턴 debug
        System.out.println("================123123=========================");


        log.info("boardList",boardList);

        return ResponseEntity.ok().body(boardList);
        // TODO : 5. 데이터들을 controller 가 client 리턴. 이 부분에 대한 확인은 PostMan 으로 체크
        //        FrontEnd 에서 console.log(boardList) <<
        // fetch -> .then      .json() -> .then (log) PostMan 처럼 확인할 수 있다
    }


    //삭제
//deleteBoard(session HttpSession, boadNo : long) : ResponseEntity<?> @DeleteMapping("/api/v1/boards/{boardNo}")

//    @DeleteMapping("/api/v1/boards/{boardNo}")
//    public ResponseEntity<?> deleteBoard(
//            HttpSession session,
//            @PathVariable("boardNo") Long boardNo
//    ) {
//        log.info("/api/v1/boards/{} DELETE!!", boardNo);
//
//        try {
//
//
//            return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//    }
//
//        // 삭제 작업의 성공 여부에 따라 응답을 반환??
//       return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
//    }
//
//    // 다른 컨트롤러 메서드들
//}





    //조회
//    + findOne(boadNo : long, model : Model, session HttpSession) : String @GetMapping("/board/detail")



    //창여는거=> 비동기
    //경로문제
    @GetMapping("/board/list")
    public String showBoardList() {
        log.info("asdsad");
        return "board/community";
    }












    //관리자페이지 boardlist 가져오기
    @GetMapping("api/v1/boards/admin")
    public ResponseEntity<?> getBoardListByAdmin(PageDTO dto) {
        ListResponseDTO<BoardsByAdminResponseDTO, User> boardListByAdmin = boardService.getBoardListByAdmin(dto);
        ListResponseDTO<BoardsByAdminResponseDTO, User> boardsByAdminResponseDTOUserListResponseDTO = boardService.todayBoardByAdmin(dto);


        return ResponseEntity
                .ok()
                .body(boardListByAdmin);
    }


}

