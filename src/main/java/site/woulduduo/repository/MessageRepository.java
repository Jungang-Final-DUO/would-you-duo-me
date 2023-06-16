package site.woulduduo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.woulduduo.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select message_content, message_from from duo_message where limit 1 order by message_time desc", nativeQuery = true)
    List<Message> findRecentMessage(Long chattingNo);
}
