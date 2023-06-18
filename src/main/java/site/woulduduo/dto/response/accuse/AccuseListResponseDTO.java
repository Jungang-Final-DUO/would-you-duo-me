package site.woulduduo.dto.response.accuse;

import lombok.*;
import site.woulduduo.entity.Accuse;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccuseListResponseDTO {

    private long accuseNo;
    private String userAccount;
    private String accuseType;
    private String accuseETC;


    public AccuseListResponseDTO(Accuse accuse){
        this.accuseNo=accuse.getAccuseNo();
        this.userAccount=accuse.getUser().getUserAccount();
        this.accuseType=accuse.getAccuseType();
        this.accuseETC=accuse.getAccuseEtc();
    }
}
