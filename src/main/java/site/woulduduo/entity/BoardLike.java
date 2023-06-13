package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "duo_board_like")
@IdClass(BoardLikeCompositeKey.class)
public class BoardLike {

    @Id
    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_account")
    private User user;
}
