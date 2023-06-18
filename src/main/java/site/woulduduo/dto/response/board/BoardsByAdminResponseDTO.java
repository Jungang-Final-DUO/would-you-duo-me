package site.woulduduo.dto.response.board;

import lombok.*;
import site.woulduduo.entity.Board;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardsByAdminResponseDTO {

    private long boardNo;
    private String userNickName;
    private String boardTitle;
    private LocalDateTime boardWrittenDate;
    private int boardViewCount;


    public BoardsByAdminResponseDTO(Board board){
        this.boardNo=board.getBoardNo();
        this.userNickName=board.getUser().getUserNickname();
        this.boardTitle=board.getBoardTitle();
        this.boardWrittenDate=board.getBoardWrittenDate();
        this.boardViewCount=board.getBoardViewCount();


    }

}


