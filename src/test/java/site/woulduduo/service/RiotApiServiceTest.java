package site.woulduduo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import site.woulduduo.enumeration.Tier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("존재하지 않는 소환사 이름을 넣으면 NoSummonerException이 발생해야 한다.")
    public void noSummonerTest() {

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            riotApiService.getEncryptedSummonerId("adfbrer");
        });

    }

    @Test
    @DisplayName("stargazer의 getTier를 하면 PLA 가 나와야 한다")
    public void getTierTest() {

        Tier tier = riotApiService.getTier("stargazer");

        assertEquals(Tier.PLA, tier);

    }

    @Test
    @DisplayName("랭크 정보가 없는 사람의 getTier를 하면 오류가 발생해야 한다")
    public void getTierExceptionTest() {

        Tier tier = riotApiService.getTier("G1HA");

        assertEquals(Tier.UNR, tier);
    }

}