package site.woulduduo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Matching;
import site.woulduduo.entity.Point;

public interface PointRepository extends JpaRepository<Point, Long> {

    boolean existsByMatching(Matching matching);
}
