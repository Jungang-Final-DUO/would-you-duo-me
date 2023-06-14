package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

import java.util.List;

public interface UserRepository extends
        JpaRepository<User,String> {
    @Query(value="SELECT user_current_point FROM duo_user WHERE user_account =:userAccount ", nativeQuery = true)
    Long findByUserCurrentPoint(String userAccount);


}
