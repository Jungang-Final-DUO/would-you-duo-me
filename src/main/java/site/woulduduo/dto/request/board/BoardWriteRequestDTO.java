package site.woulduduo.dto.request.board;

import lombok.*;
import site.woulduduo.entity.Board;
import site.woulduduo.enumeration.BoardCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BoardWriteRequestDTO {

    @NotBlank
    @Size(min =1 , max =50)
    private String boardTitle;

    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;

    private String boardContent;

//    public BoardWriteRequestDTO(Board saved) {
//    }


    public Board toEntity(){
        return Board.builder()
                .boardTitle(this.boardTitle)
                .boardCategory(this.boardCategory)
                .boardContent(this.boardContent)
                .build();

    }
}
