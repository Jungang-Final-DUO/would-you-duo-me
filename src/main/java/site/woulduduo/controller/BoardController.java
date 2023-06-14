package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.Service.BoardService;
import site.woulduduo.dto.request.board.BoardWriteRequestDTO;

import javax.servlet.http.HttpSession;

@RestController
    @Slf4j
    @RequiredArgsConstructor
    @RequestMapping("/api/v1/board") //경로

    public class BoardController {
        private  final BoardService boardService;

        //게시물 등록

        //test
//        @Parameters({
//                @Parameter()
//        })
        @PostMapping("/board/write")
        public writeBoard(HttpSession session, BoardWriteRequestDTO){

        }

    }
