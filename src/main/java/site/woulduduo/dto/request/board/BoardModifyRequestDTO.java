package site.woulduduo.dto.request.board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BoardModifyRequestDTO {
    private Long boardNo;

    @NotBlank
    @Size(min=1,max =50)
    private String boardTitle;

    private String boardContent;

}
