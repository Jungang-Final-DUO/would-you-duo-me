package site.woulduduo.entity;

import lombok.*;
import site.woulduduo.enumeration.AdType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = "pointList")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "adNo")
@Builder
@Entity
@Table(name = "duo_ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_no")
    private Long adNo;

    @Column(name = "ad_type", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private AdType adType;

    @Column(name = "ad_image", nullable = false, length = 1000)
    private String adImage;

    @Column(name = "ad_point", nullable = false, length = 3)
    private Integer adPoint;

    @Column(name = "ad_title", nullable = false, length = 30)
    private String adTitle;

    // 포인트 지급 내역
    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Point> pointList = new ArrayList<>();

    public void addPoint(Point point) {
        pointList.add(point);
        if (this != point.getAd()) {
            point.setAd(this);
        }
    }
}
