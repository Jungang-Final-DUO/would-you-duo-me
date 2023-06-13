package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString(exclude = {"chatting"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "messageNo")
@Builder
@Entity
@Table(name = "duo_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;

    @OneToOne
    @JoinColumn(name = "user_account")
    private User user;

    @Column(length = 100)
    private String messageContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime messageTime;

    @Builder.Default
    @Column(columnDefinition = "TINYINT(1)")
    private boolean messageIsRead = false;

    @ManyToOne
    @JoinColumn(name = "chatting_no")
    private Chatting chatting;

}
