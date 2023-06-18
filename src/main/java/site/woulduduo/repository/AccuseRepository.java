package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

public interface AccuseRepository extends JpaRepository<Accuse,Long> {

    long countByUser(User user);

    @Query(value = "SELECT COUNT(*) FROM duo_accuse WHERE accuse_written_date >= DATE(NOW()) AND accuse_written_date < DATE_ADD(DATE(NOW()), INTERVAL 1 DAY)",nativeQuery = true)
    int findAllWithAccuseWrittenDate();












}
