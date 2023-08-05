package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.RecentMatch;

public interface RecentMatchRepository extends JpaRepository<RecentMatch, Long> {

    @Query("delete from RecentMatch r where r.user.userAccount = :UserAccount")
    void deleteAllByUserAccount(String UserAccount);

}
