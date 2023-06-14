package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.FetchType;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString(exclude = {"chatting", "user"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "messageNo")
@Builder
@Entity
@Table(name = "duo_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_no")
    private Long messageNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_from")
    private User user;

    @Column(length = 100, name = "message_content")
    private String messageContent;

    @CreationTimestamp
    @Column(updatable = false, name = "mesage_time")
    private LocalDateTime messageTime;

    @Builder.Default
    @Column(columnDefinition = "TINYINT(1)", name = "message_is_read")
    private boolean messageIsRead = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_no")
    private Chatting chatting;

}
