package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Follow;
import site.woulduduo.entity.FollowCompositeKey;
import site.woulduduo.entity.User;

public interface FollowRepository extends JpaRepository<Follow, FollowCompositeKey> {

//    @Query(value="SELECT count(*) FROM duo_user_follow WHERE follow_to =:userAccount ", nativeQuery = true)
//    Long findByUserfollowTo(String userAccount);

}
