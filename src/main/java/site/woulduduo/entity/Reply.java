package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = false, length = 100)
    private String replyContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime replyWrittenDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;
}
