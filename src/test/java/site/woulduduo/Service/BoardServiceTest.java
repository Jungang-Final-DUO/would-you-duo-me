//package site.woulduduo.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.JpaRepository;
//import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
//import site.woulduduo.entity.Board;
//import site.woulduduo.enumeration.BoardCategory;
//import site.woulduduo.repository.BoardRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class BoardServiceTest {
//    @Autowired
//    private BoardService boardService;
//
//
//    @Test
//    @DisplayName("bulk insert")
//    void bulkinser() {
//        for (int i = 1; i <= 10; i++) {
//            BoardWriteRequestDTO build = BoardWriteRequestDTO.builder()
//                    .boardTitle("아라랑")
//                    .boardContent("jpa넘어렵 ㅜ")
//                    .boardCategory(BoardCategory.NOTICE)
//                    .build();
//
//            System.out.println("build = " + build);
//
//            boolean b = boardService.writeBoard(build);
//            assertTrue(b);
//
//        }
//
//
//    }
//    @Test
//    @DisplayName("update")
//    void update(){
//        for(int 1 =1;)
//    }
//
//}