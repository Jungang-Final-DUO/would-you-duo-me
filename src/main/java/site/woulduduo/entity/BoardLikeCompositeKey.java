package site.woulduduo.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLikeCompositeKey implements Serializable {

    private Board board;

    private User user;
}
