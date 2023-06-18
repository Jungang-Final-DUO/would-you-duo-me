package site.woulduduo.dto.request.chatting;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MatchingFixRequestDTO {

    private long matchingNo;
    private LocalDate matchingDate;

}
