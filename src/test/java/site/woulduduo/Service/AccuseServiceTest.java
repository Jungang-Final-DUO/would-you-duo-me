package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class AccuseServiceTest {

    @Autowired
    AccuseService accuseService;

    @Test
    @DisplayName("Accuse list dto 변환")
    void saveTest() {
        List<AccuseListResponseDTO> accuseListByAdmin =
                accuseService.getAccuseListByAdmin();

        System.out.println("accuseListByAdmin = " + accuseListByAdmin);
    }

}