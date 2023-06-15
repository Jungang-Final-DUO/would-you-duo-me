package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;

public interface AccuseRepository extends JpaRepository<Accuse,Long> {

    long countByUser(User user);










}
