package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AccuseRepository extends JpaRepository<Accuse,Long> {

    long countByUser(User user);

    @Query(value = "SELECT COUNT(*) FROM duo_accuse WHERE accuse_written_date >= DATE(NOW()) AND accuse_written_date < DATE_ADD(DATE(NOW()), INTERVAL 1 DAY)",nativeQuery = true)
    int findAllWithAccuseWrittenDate();












}
