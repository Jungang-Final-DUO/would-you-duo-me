package site.woulduduo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends
        JpaRepository<User,String> {

    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_join_date = :userJoinDate",nativeQuery = true)
    int findAllWithJoinDate(LocalDate userJoinDate);

    long countByUserAccount(String userAccount);

//    @Query(value="SELECT user_current_point FROM duo_user WHERE user_account =:userAccount ", nativeQuery = true)
//    Long findByUserCurrentPoint(String userAccount);


}
