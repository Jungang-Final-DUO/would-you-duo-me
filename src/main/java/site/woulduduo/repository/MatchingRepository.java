package site.woulduduo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Matching findByMatchingNo(long matchingNo);

    @Query("SELECT m FROM Matching m WHERE m.chatting.chattingTo.userAccount = :userAccount AND m.matchingReviewRate IS NOT NULL\n")
    Page<Matching> findOneByChattingTo(String userAccount, Pageable pageable);

    @Query("SELECT m FROM Matching m WHERE m.chatting.chattingTo.userAccount = :userAccount")
    Page<Matching> findOneByChattingToOnMyPage(String userAccount, Pageable pageable);

    @Query("SELECT m FROM Matching m WHERE m.chatting.chattingFrom.userAccount = :userAccount")
    Page<Matching> findOneByChattingFromOnMyPage(String userAccount, Pageable pageable);
}
