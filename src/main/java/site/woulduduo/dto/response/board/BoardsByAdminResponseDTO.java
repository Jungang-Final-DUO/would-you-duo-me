package site.woulduduo.dto.response.board;

import lombok.*;
import site.woulduduo.entity.Board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String boardWrittenDate;
    private int boardViewCount;


    public BoardsByAdminResponseDTO(Board board){
        this.boardNo=board.getBoardNo();
        this.userNickName=board.getUser().getUserNickname();
        this.boardTitle=board.getBoardTitle();
        this.boardWrittenDate=localDateTime(board);
        this.boardViewCount=board.getBoardViewCount();


    }

    public String localDateTime(Board board){
        LocalDateTime date = board.getBoardWrittenDate();
        LocalDateTime localDateTime = date.withNano(0);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = localDateTime.format(dateTimeFormatter).replace("T", "");

        return datetime;

    }

}


