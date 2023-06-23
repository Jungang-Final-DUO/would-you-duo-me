package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.request.accuse.UserAccuseRequestDTO;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.entity.Accuse;
import site.woulduduo.service.AccuseService;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor


    public class AccuseController {

    private final AccuseService accuseService;

    @GetMapping("/api/v1/user/accuse")
    public ResponseEntity<?> getAccuseListByAdmin(HttpSession session,
                                                  PageDTO dto){
        log.info("{}dtozz",dto);
        ListResponseDTO<AccuseListResponseDTO, Accuse> accuseListByAdmin = accuseService.getAccuseListByAdmin(dto);
        log.info("{}accuseListByAdmin",accuseListByAdmin);


        return ResponseEntity
                .ok()
                .body(accuseListByAdmin);
    }

    @PostMapping("/user/accuse")
    public String accuseUser(HttpSession session, UserAccuseRequestDTO dto){
        accuseService.accuseUser(dto);


        return "admin/accuseModal";
    }
}
