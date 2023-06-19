package site.woulduduo.dto.request.accuse;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccuseRequestDTO {
    private String userAccount;
    private List<String> accuseType;
    private String accuseEtc;



}
