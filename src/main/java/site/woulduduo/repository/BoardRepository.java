package site.woulduduo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
//메서드 쿼리 커스텀 할라면 넣어야 함
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public interface BoardRepository extends JpaRepository<Board,Long> {



//    List<Board> findByBoardTitleContainingIgnoreCase(String keyword, Pageable pageable);
//    List<Board> findByBoardCategory(BoardCategory boardCategory, Pageable pageable);

    //해당 id 게시글 작성
    long countByUser(User user);

    @Query(value = "SELECT COUNT(*) FROM duo_board WHERE board_written_date >= DATE(NOW()) AND board_written_date < DATE_ADD(DATE(NOW()), INTERVAL 1 DAY)",nativeQuery = true)
    int findAllWithBoardWrittenDate();




}
