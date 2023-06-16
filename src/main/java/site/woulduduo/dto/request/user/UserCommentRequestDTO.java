package site.woulduduo.dto.request.user;

import lombok.*;
import site.woulduduo.enumeration.Position;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCommentRequestDTO {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Position userPosition;

    @NotNull
    @Column(length = 100)
    private String userComment;

    @NotNull
    @Size(min = 100, max = 999)
    private int userMatchingPoint;
}
