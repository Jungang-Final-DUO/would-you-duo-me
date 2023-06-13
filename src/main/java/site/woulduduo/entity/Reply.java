package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString(exclude = {"board", "user"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "replyNo")
@Builder
@Entity
@Table(name = "duo_reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_no")
    private Long replyNo;

    @Column(name = "reply_content", nullable = false, length = 100)
    private String replyContent;

    @CreationTimestamp
    @Column(name = "reply_written_date", updatable = false)
    private LocalDateTime replyWrittenDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account", insertable = false, updatable = false)
    private User user;
}
