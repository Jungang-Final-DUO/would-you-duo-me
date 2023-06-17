package site.woulduduo.dto.response.chatting;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ChattingListResponseDTO {

    private long chattingNo;
    private String profileImage;
    private String userNickname;
    private String messageContent;
}
