package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import site.woulduduo.enumeration.BoardCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long boardNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @Column(nullable = false, length = 50)
    private String boardTitle;

    @Column(nullable = false, length = 1000)
    private String boardContent;

    @Builder.Default
    private Long boardLike = 0L;

    @Builder.Default
    private Long boardViewCount = 0L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime boardWrittenDate;
}
