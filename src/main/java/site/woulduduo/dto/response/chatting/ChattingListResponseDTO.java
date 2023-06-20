package site.woulduduo.dto.response.chatting;

import lombok.*;
import site.woulduduo.entity.Chatting;
import site.woulduduo.enumeration.MatchingStatus;

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
    private MatchingStatus matchingStatus;

    public ChattingListResponseDTO(Chatting chatting){
        this.chattingNo = chatting.getChattingNo();
        this.matchingStatus = chatting.getMatchingList().get(0).getMatchingStatus();
    }
}
