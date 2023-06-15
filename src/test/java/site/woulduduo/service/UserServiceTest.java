package site.woulduduo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.enumeration.AdminViewType;
import site.woulduduo.enumeration.Position;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class UserServiceTest {

    @Autowired
    UserService userService;

    @BeforeEach
    void userInsert() {

    }

    @Test
    @DisplayName("회원의 프로필 카드 등록에 성공해야 한다.")
    void registerDUO() {
        UserCommentRequestDTO userCommentRequestDTO = UserCommentRequestDTO.builder()
                .userPosition(Position.MID)
                .userComment("안녕하세요 트롤아닙니다.")
                .userMatchingPoint(500)
                .build();

        boolean b = userService.registerDUO(userCommentRequestDTO);

        assertTrue(b);
    }

    @Test
    @DisplayName("관리자 유저 리스트 dto 변환")
    void userExchangeDTO(){
        AdminSearchType build = AdminSearchType.builder()
                .adminViewType(AdminViewType.TODAY)
                .build();

        userService.getUserListByAdmin(build);
    }

}