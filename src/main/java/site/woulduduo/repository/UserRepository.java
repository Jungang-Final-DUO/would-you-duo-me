package site.woulduduo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends
        JpaRepository<User,String> {

    Page<User> findAll(Pageable pageable);

//    @Query(value="SELECT user_current_point FROM duo_user WHERE user_account =:userAccount ", nativeQuery = true)
//    Long findByUserCurrentPoint(String userAccount);


}
