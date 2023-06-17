package site.woulduduo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @BeforeEach
    void addBulkData() {

    }

}