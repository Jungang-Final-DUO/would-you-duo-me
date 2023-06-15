package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.woulduduo.entity.Chatting;

import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {


    List<Chatting> findByChattingFromAndChattingTo(String userAccount);

}
