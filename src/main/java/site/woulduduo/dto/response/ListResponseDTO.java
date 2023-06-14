package site.woulduduo.dto.response;

import lombok.*;
import site.woulduduo.dto.response.page.PageResponseDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ListResponseDTO<T> {

    private int count;

    private PageResponseDTO<T> pageInfo;

    private List<T> list;

}
