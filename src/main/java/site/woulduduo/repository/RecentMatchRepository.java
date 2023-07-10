package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.RecentMatch;

public interface RecentMatchRepository extends JpaRepository<RecentMatch, Long> {
}
