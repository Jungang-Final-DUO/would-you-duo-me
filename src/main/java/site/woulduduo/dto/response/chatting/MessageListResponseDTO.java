package site.woulduduo.dto.response.chatting;

import lombok.*;

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
}
