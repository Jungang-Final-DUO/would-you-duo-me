package site.woulduduo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BoardLikeCompositeKey implements Serializable {

    private Board boardNo;

    private User userAccount;
}
