package site.woulduduo.dto.request.chatting;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageRequestDTO {

    private long chattingNo;
    private String messageContent;
    private String messageFrom;
}
