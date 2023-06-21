package site.woulduduo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.woulduduo.entity.User;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT COUNT(*) FROM User u WHERE u.userAccount = :email")
    int countByUserEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_join_date = :userJoinDate",nativeQuery = true)
    int findAllWithJoinDate(LocalDate userJoinDate);

    long countByUserAccount(String userAccount);

    User findByUserAccount(String userAccount);

    // 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_nickname = :nickname", nativeQuery = true)
    int countByUserNickname(@Param("nickname") String nickname);

    // 롤 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE lol_nickname = :lolNickname", nativeQuery = true)
    int countByLolNickname(@Param("lolNickname") String lolNickname);

//    User findByUserAccount(String userAccount);
}
