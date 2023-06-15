package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void saveTest(){
        List<Board> all = boardRepository.findAll();
        System.out.println("all = " + all);
        User user4 = User.builder()
                .userAccount("345")
                .userNickname("345")
                .userPassword("345")
                .userCurrentPoint(345)
                .userBirthday(LocalDate.of(1999,11,07))
                .lolNickname("345")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
                .boardList(all)
                .build();
        User save2 = userRepository.save(user4);

        Board board = Board.builder()
                .user(save2)
                .boardTitle("게시글제목")
                .boardContent("게시글내용")
                .boardCategory(BoardCategory.ACCUSE)
                .build();

        Board board2 = Board.builder()
                .user(save2)
                .boardTitle("게시글제목1")
                .boardContent("게시글내용1")
                .boardCategory(BoardCategory.ACCUSE)
                .build();

        Board board3 = Board.builder()
                .user(save2)
                .boardTitle("게시글제목2")
                .boardContent("게시글내2")
                .boardCategory(BoardCategory.ACCUSE)
                .build();

        Board asave = boardRepository.save(board);
        Board asave1 = boardRepository.save(board2);
        Board asave2 = boardRepository.save(board3);



        long count = boardRepository.countByUser((save2));
        System.out.println("count = " + count);
    }

}