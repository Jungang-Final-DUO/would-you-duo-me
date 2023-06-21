package site.woulduduo.dto.response.chatting;

import lombok.*;
import site.woulduduo.entity.Chatting;
import site.woulduduo.entity.Matching;
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
    private long matchingNo;
    private MatchingStatus matchingStatus;

    public ChattingListResponseDTO(Chatting chatting){
        this.chattingNo = chatting.getChattingNo();

        try {
            Matching matching = chatting.getMatchingList().get(0);
            this.matchingNo = matching.getMatchingNo();
            this.matchingStatus = matching.getMatchingStatus();
        } catch (IndexOutOfBoundsException e) {
            this.matchingNo = 0;
            this.matchingStatus = null;
        }

    }
}
