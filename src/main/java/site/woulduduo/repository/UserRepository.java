package site.woulduduo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.User;

import java.time.LocalDate;

public interface UserRepository extends
        JpaRepository<User,String> {

    //아이디로 검색한 정보보기 + 페이징
    Page<User> findByUserAccountContaining(String userAccount, Pageable pageable);

    //날짜별 가입 user 보기
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_join_date = :userJoinDate",nativeQuery = true)
    int findAllWithJoinDate(LocalDate userJoinDate);

    //nickname으로 User 객체 찾기
    @Query(value = "SELECT * FROM duo_user WHERE user_nickname = :userNickName",nativeQuery = true)
    User findByNickName(String userNickName);
    //가입수
    long countByUserAccount(String userAccount);

    // 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE user_nickname = :nickname", nativeQuery = true)
    int countByUserNickname(@Param("nickname") String nickname);

    // 롤 닉네임 중복검사를 위한 쿼리문
    @Query(value = "SELECT COUNT(*) FROM duo_user WHERE lol_nickname = :lolNickname", nativeQuery = true)
    int countByLolNickname(@Param("lolNickname") String lolNickname);



    User findByUserAccount(String userAccount);
}
