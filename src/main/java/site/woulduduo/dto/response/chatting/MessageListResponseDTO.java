package site.woulduduo.dto.response.chatting;

import lombok.*;
import site.woulduduo.entity.Message;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MessageListResponseDTO {

    private String messageFrom;
    private String messageContent;
    private LocalDateTime messageTime;

    public MessageListResponseDTO(Message m) {
        this.messageFrom = m.getUser().getUserNickname();
        this.messageContent = m.getMessageContent();
        this.messageTime = m.getMessageTime();
    }
}
