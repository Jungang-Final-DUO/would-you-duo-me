package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;

public interface BoardRepository extends
        JpaRepository<Board,Long> {

    //해당 id 게시글 작성
    long countByUser(User user);
}