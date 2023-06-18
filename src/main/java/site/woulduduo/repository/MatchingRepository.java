package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Matching findByMatchingNo(long matchingNo);
}
