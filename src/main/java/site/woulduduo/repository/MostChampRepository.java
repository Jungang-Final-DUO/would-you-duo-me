package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.MostChamp;

public interface MostChampRepository extends JpaRepository<MostChamp, Long> {

    MostChamp getAllByUser_UserAccountAndMostNo(String userAccount, int mostNo);

    @Query("delete from MostChamp m where m.user.userAccount = :userAccount")
    void deleteAllByUserAccount(String userAccount);

}
