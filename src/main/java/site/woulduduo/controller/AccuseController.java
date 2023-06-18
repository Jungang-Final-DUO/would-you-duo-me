package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.enumeration.AdminViewType;
import site.woulduduo.service.AccuseService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/accuse") //경로
public class AccuseController {
    private final AccuseService accuseService;

    @GetMapping("/{type}/{page}")
    public List<?> getAccuseListByAdmin(HttpSession session, @PathVariable AdminViewType type, @PathVariable int Page){
        List<AccuseListResponseDTO> accuseListByAdmin = accuseService.getAccuseListByAdmin();
        return accuseListByAdmin;
    }
}
