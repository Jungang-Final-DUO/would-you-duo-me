package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {


    //메서드 쿼리 커스텀 할라면 넣어야 함

}
