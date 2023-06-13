package site.woulduduo.entity;

import lombok.*;
import site.woulduduo.enumeration.MatchingStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString(exclude = {"chatting"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "matchingNo")
@Builder
@Entity
@Table(name = "duo_matching")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingNo;

    private LocalDate matchingDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private MatchingStatus matchingStatus;

    @Column(nullable = false)
    private Integer matchingPoint;

    @Column(length = 100)
    private String matchingReviewContent;

    @Column(columnDefinition = "INT(1)")
    private Integer matchingReviewRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_no")
    private Chatting chatting;
}
