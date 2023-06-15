package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(false)
class AccuseRepositoryTest {
    @Autowired
    AccuseRepository accuseRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자 저장")
    void saveTest(){
        List<Accuse> all = accuseRepository.findAll();
        System.out.println("all = " + all);
        User user4 = User.builder()
                .userAccount("345")
                .userNickname("345")
                .userPassword("345")
                .userCurrentPoint(345)
                .userBirthday(LocalDate.of(1999,11,07))
                .lolNickname("345")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
                .accuseList(all)
                .build();
        User save2 = userRepository.save(user4);

        long count = accuseRepository.countByUser((save2));
        System.out.println("count = " + count);
    }

}