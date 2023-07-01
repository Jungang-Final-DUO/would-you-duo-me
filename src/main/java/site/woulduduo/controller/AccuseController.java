package site.woulduduo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.woulduduo.dto.request.accuse.UserAccuseRequestDTO;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.dto.response.login.LoginUserResponseDTO;
import site.woulduduo.enumeration.AdminViewType;
import site.woulduduo.enumeration.Role;
import site.woulduduo.service.AccuseService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static site.woulduduo.util.LoginUtil.LOGIN_KEY;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/accuse") //경로
public class AccuseController {
    private final AccuseService accuseService;

    @GetMapping("/{type}/{page}")
    public ResponseEntity<?> getAccuseListByAdmin(HttpSession session,
                                                  @PathVariable AdminViewType type,
                                                  @PathVariable int page){
        if (!((LoginUserResponseDTO) session.getAttribute(LOGIN_KEY)).getRole().equals(Role.ADMIN)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<AccuseListResponseDTO> accuseListByAdmin = accuseService.getAccuseListByAdmin();
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
