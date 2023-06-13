package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import site.woulduduo.enumeration.BoardCategory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "boardNo")
@Builder
@Entity
@Table(name = "duo_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @Column(name = "board_title", nullable = false, length = 50)
    private String boardTitle;

    @Column(name = "board_content", nullable = false, length = 1000)
    private String boardContent;

    @Builder.Default
    @Column(name = "board_like")
    private Long boardLike = 0L;

    @Builder.Default
    @Column(name = "board_view_count")
    private Long boardViewCount = 0L;

    @Column(name = "board_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    @CreationTimestamp
    @Column(name = "board_written_date", updatable = false)
    private LocalDateTime boardWrittenDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_no")
    private List<Reply> replyList;
}
