package site.woulduduo.entity;

import lombok.*;
import org.hibernate.type.EnumType;

import javax.persistence.*;

@Setter
@Getter
@ToString
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

    @Column(name = "ad_type",nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private AdType adType;
    @Column(name = "ad_image",nullable = false, length = 1000)
    private String adImage;
    @Column(name = "ad_point",nullable = false,length = 3)
    private Integer adPoint;

    @Column(name = "ad_title",nullable = false,length = 30)
    private String adTitle;


}
