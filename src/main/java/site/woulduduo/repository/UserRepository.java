package site.woulduduo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.entity.Accuse;
import org.springframework.data.jpa.repository.Query;
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

    // 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_nickname = :nickname", nativeQuery = true)
    int countByUserNickname(@Param("nickname") String nickname);

    // 롤 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE lol_nickname = :lolNickname", nativeQuery = true)
    int countByLolNickname(@Param("lolNickname") String lolNickname);

    User findByUserAccount(String userAccount);
}
