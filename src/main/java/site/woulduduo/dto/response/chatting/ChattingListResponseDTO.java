package site.woulduduo.dto.response.chatting;

import lombok.*;
import site.woulduduo.entity.Chatting;

@Getter
@Setter
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
    private int messageUnreadCount;

    public ChattingListResponseDTO(Chatting chatting){
        this.chattingNo = chatting.getChattingNo();
    }
}
