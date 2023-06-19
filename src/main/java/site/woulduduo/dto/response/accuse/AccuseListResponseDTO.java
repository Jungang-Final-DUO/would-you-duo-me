package site.woulduduo.dto.response.accuse;

import lombok.*;
import site.woulduduo.entity.Accuse;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDate accuseWrittenDate;


    public AccuseListResponseDTO(Accuse accuse){
        this.accuseNo=accuse.getAccuseNo();
        this.userAccount=accuse.getUser().getUserAccount();
        this.accuseType=accuse.getAccuseType();
        this.accuseETC=accuse.getAccuseEtc();
        this.accuseWrittenDate=accuseDate(accuse.getAccuseWrittenDate());
    }

    public LocalDate accuseDate(LocalDateTime dateTime){

        LocalDate localDate = dateTime.toLocalDate();
        return localDate;
    }
}
