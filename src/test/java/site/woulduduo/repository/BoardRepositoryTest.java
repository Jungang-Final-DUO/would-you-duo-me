package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;
import java.util.List;
@SpringBootTest
@Transactional
@Rollback(false)
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("게시글 save 및 해당 id가 쓴글 count")
    void saveTest() {
        List<Board> all = boardRepository.findAll();
        System.out.println("all = " + all);
        User user4 = User.builder()
                .userAccount("345")
                .userNickname("345")
                .userPassword("345")
                .userCurrentPoint(345)
                .userBirthday(LocalDate.of(1999, 11, 07))
                .lolNickname("345")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
                .boardList(all)
                .build();
        User save2 = userRepository.save(user4);

        for (int i = 0; i < 200; i++) {
            Board board = Board.builder()
                    .user(save2)
                    .boardTitle("게시글제목1"+i)
                    .boardContent("게시글내용1"+i)
                    .boardCategory(BoardCategory.ACCUSE)
                    .build();


            Board asave = boardRepository.save(board);
        }



        long count = boardRepository.countByUser((save2));
        System.out.println("count = " + count);
    }

    @Test
    @DisplayName("전체 board count 확인")
    void save() {
        List<Board> all = boardRepository.findAll();
        int size = all.size();
        System.out.println("size = " + size);
    }

    @Test
    @DisplayName("전체 board count 확인")
    void accountTest() {
        List<Board> all = boardRepository.findAll();
        int size = all.size();
        System.out.println("size = " + size);
    }

    @Test
    @DisplayName("금일 작성 게시물 count 확인")
    void todaySaveTest() {

        int allWithBoardWrittenDate = boardRepository.findAllWithBoardWrittenDate();
        System.out.println("allWithJoinDate = " + allWithBoardWrittenDate);


    }

    @Test
    @DisplayName("boardNO 로 게시물 찾고 삭제")
    void findDeleteBoard() {

        Board byBoardNo = boardRepository.findByBoardNo(225);
        System.out.println("byBoardNo = " + byBoardNo);

       // boolean b = boardRepository.deleteByBoardNo(217);

       // System.out.println("123asd = " + b);

    }
}