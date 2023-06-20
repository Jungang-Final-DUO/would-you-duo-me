package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.woulduduo.entity.User;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT COUNT(*) FROM User u WHERE u.userNickname = :nickname")
    int countByUserNickname(String nickname);

    @Query(value = "SELECT COUNT(*) FROM User u WHERE u.lolNickname = :lolNickname")
    int countByLolNickname(String lolNickname);

    @Query(value = "SELECT COUNT(*) FROM User u WHERE u.userAccount = :email")
    int countByUserEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_join_date = :userJoinDate",nativeQuery = true)
    int findAllWithJoinDate(LocalDate userJoinDate);

    long countByUserAccount(String userAccount);

    User findByUserAccount(String userAccount);
}
