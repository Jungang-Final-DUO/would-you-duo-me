package site.woulduduo.entity;

import lombok.*;
import site.woulduduo.enumeration.MatchingStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "matching", fetch = FetchType.LAZY)
    private List<Point> pointList = new ArrayList<>();

    public void addPoint(Point point) {
        pointList.add(point);
        if (this != point.getMatching()) {
            point.setMatching(this);
        }
    }
}
