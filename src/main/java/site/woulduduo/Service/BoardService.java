package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.board.BoardModifyRequestDTO;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.board.BoardsByAdminResponseDTO;
import site.woulduduo.dto.response.page.PageResponseDTO;
import site.woulduduo.entity.Board;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.BoardCategory;
import site.woulduduo.repository.BoardLikeRepository;
import site.woulduduo.repository.BoardRepository;
import site.woulduduo.repository.ReplyRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BoardLikeRepository boardLikeRepository;


    //조회
    private Board getBoard(Long boardNo){
        return  boardRepository.findById(boardNo)
                .orElseThrow(
                        () -> new RuntimeException(
                                boardNo + "번 게시물이 존재하지 않습니다."

                        )
                );
    }

    //dto를 받아서 entity 로 저장
    //쓰기
    public boolean writeBoard(/*HttpSession session, */BoardWriteRequestDTO dto) {

        //세션에서정보가져오기


        //게시글 쓰기 board 엔터티 생성
        Board saved = boardRepository.save(dto.toEntity());
        System.out.println("saved = " + saved);
        if(saved != null) {
            return true;
        }
        return false;


    }




    //수정
  public Long modifyBoard(BoardModifyRequestDTO dto) {

       //수정 전 데이터 조회
      final Board boardEntity = getBoard(dto.getBoardNo());
//        if (boardEntity == null) {
//            return false;
//        }

       //수정 시작
        boardEntity.setBoardTitle(dto.getBoardTitle());
        boardEntity.setBoardContent(dto.getBoardContent());
        boardEntity.setBoardCategory(BoardCategory.NOTICE);

        //수정완료
        Board modfiedBoard = boardRepository.save(boardEntity);

        return modfiedBoard.getBoardNo();
        //반환값 설정

    }

    //전체 BoardList DTO 변환 (Admin)+ 페이징
    public ListResponseDTO<BoardsByAdminResponseDTO,User> getBoardListByAdmin(PageDTO dto){
        Pageable pageable = PageRequest.of(
                dto.getPage()-1,
                dto.getSize(),
                Sort.by("boardWrittenDate").descending()
        );
        Page<Board> all = boardRepository.findAll(pageable);

        List<BoardsByAdminResponseDTO> collect = all.stream()
                .map(BoardsByAdminResponseDTO::new)
                .collect(toList());

        return ListResponseDTO.builder()
                .count(all.getSize())
                .pageInfo(new PageResponseDTO(all))
                .list(collect)
                .build();
    }

    //금일 작성 게시물 (ADMIN)
    public ListResponseDTO<BoardsByAdminResponseDTO,User> todayBoardByAdmin(PageDTO dto){

        Pageable pageable = PageRequest.of(
                dto.getPage()-1,
                dto.getSize(),
                Sort.by("boardWrittenDate").descending()
        );
        Page<Board> all = boardRepository.findAll(pageable);
        List<Board> todayBoardList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Board board : all) {
            System.out.println("boardsByAdminResponseDTO = " + board);
            LocalDateTime writtenDate = board.getBoardWrittenDate();
            LocalDate localDate = writtenDate.toLocalDate();
            if(localDate!=null&&localDate.equals(currentDate)){
                todayBoardList.add(board);
            }
        }

        List<BoardsByAdminResponseDTO> collect = todayBoardList.stream()
                .map(BoardsByAdminResponseDTO::new)
                .collect(toList());

        return ListResponseDTO.builder()
                .count(collect.size())
                .pageInfo(new PageResponseDTO(all))
                .list(collect)
                .build();


    }

}
