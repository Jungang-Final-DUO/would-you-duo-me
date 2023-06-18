package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Follow;
import site.woulduduo.entity.FollowCompositeKey;

public interface FollowRepository extends JpaRepository<Follow, FollowCompositeKey> {

//    @Query(value = "SELECT COUNT(*) FROM duo_follow WHERE follow_to = :user",nativeQuery = true)
//    long findToByAccount(User user);


    @Query("SELECT COUNT(*) FROM Follow f WHERE f.followFrom.userAccount = :followFrom AND f.followTo.userAccount = :followTo")
    boolean existsByFollowFromAndFollowTo(String followFrom, String followTo);

}
