package site.woulduduo.dto.request.board;

import site.woulduduo.entity.Board;
import site.woulduduo.enumeration.BoardCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BoardWriteRequestDTO {

    @NotBlank
    @Size(min =1 , max =50)
    private String boardTitle;

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
