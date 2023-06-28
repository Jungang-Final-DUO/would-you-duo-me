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
        this.accuseType=accuseType(accuse,20);
        this.accuseETC=accuseEtc(accuse,20);
        this.accuseWrittenDate=accuseDate(accuse.getAccuseWrittenDate());
    }

    public LocalDate accuseDate(LocalDateTime dateTime){

        LocalDate localDate = dateTime.toLocalDate();
        return localDate;
    }
    public String accuseType(Accuse accuse, int maxLength) {
        String string = accuse.getAccuseType().toString();
        int length = string.length();
        if (length <= maxLength) {
            return string;
        }else{
            return string.substring(0,maxLength)+"...";
        }
    }

    public String accuseEtc(Accuse accuse, int maxLength) {
        if (accuse == null) {
            // accuse 객체가 null인 경우 처리
            return "";
        }

        String accuseEtc = accuse.getAccuseEtc();
        if (accuseEtc == null) {
            // accuseEtc 값이 null인 경우 처리
            return "";
        }

        int length = accuseEtc.length();
        if (length <= maxLength) {
            return accuseEtc;
        } else {
            return accuseEtc.substring(0, maxLength) + "...";
        }
    }
}
