package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = {"matching", "message"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "chattingNo")
@Builder
@Entity
@Table(name = "duo_chatting")
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chattingNo;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User chattingFrom;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User chattingTo;

    @Builder.Default
    @OneToMany(mappedBy = "chatting")
    private List<Matching> matchings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "chatting")
    private List<Message> messages = new ArrayList<>();

    //양방향 매핑에서 리스트쪽에 데이터를 추가하는 편의메서드 생성
    public void addMatching(Matching matching){
        matchings.add(matching);
        if(this != matching.getChatting()){
            matching.setChatting(this);
        }
    }

    public void addMessage(Message message){
        messages.add(message);
        if(this != message.getChatting()){
            message.setChatting(this);
        }
    }
}
