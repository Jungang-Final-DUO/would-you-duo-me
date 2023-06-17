package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Follow;
import site.woulduduo.entity.FollowCompositeKey;

public interface FollowRepository extends JpaRepository<Follow, FollowCompositeKey> {


}
