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

    private Long adNo;

    @Column(nullable= false,length = 5)

    private String adType;
    @Column(nullable = false, length = 1000)
    private String adImage;
    @Column(nullable = false,length = 3)
    private Integer adPoint;

    @Column(nullable = false,length = 30)
    private String adTitle;


}
