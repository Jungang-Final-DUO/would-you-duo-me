package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
import site.woulduduo.entity.Board;
import site.woulduduo.enumeration.BoardCategory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
@Transactional
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    @DisplayName("bulk insert")
    void bulkinser() {
        for (int i = 1; i <= 200; i++) {
            boardRepository.save(
                    Board.builder()
                            .boardTitle("아라랑")
                            .boardContent("jpa넘어렵 ㅜ")
                            .boardCategory(BoardCategory.NOTICE)
                            .build()

            );


//            Board build = Board.builder()
//                    .build();
//                    boardRepository.save(build);



       }
    }





// 강의교안
//    @Test
//    @DisplayName("bulk insert")
//    void bulkInsert() {
//        for (int i = 1; i <= 378; i++) {
//            postRepository.save(
//                    Post.builder()
//                            .title("하하호호제목" + i)
//                            .content("깔깔깔깔내용" + i)
//                            .writer("꾸까꾸까" + i)
//                            .build()
//            );
//        }
//    }

}