package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RiotApiServiceTest {

    @Autowired
    RiotApiService riotApiService;

    @Test
    @DisplayName("stargazer 소환사 이름을 넣으면 결과가 _l3OJ8Lnjiea6zwUQ4pbr6F2zYLt1W-qHbKKd62MtA0y0A 가 나와야 한다.")
    void getEncryptedSummonerId() {

        String encryptedSummonerId = riotApiService.getEncryptedSummonerId("stargazer");

        assertEquals("_l3OJ8Lnjiea6zwUQ4pbr6F2zYLt1W-qHbKKd62MtA0y0A", encryptedSummonerId);
    }

}