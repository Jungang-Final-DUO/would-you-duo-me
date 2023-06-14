package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.woulduduo.dto.riot.SummonerV4DTO;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.exception.NoSummonerException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiotApiService {

    // 라이엇 요청 uri 공통
    @Value("${riot.api.request.uri.common}")
    private String RIOT_URI;

    // 라이엇 api key
    @Value("${riot.api.key}")
    private String RIOT_API_KEY;

    private Tier getTier(String lolNickname) throws NoSummonerException {

        String encryptedSummonerId = getEncryptedSummonerId(lolNickname);

        String leagueV4RequestUri = RIOT_URI + "/league/v4/entries/by-summoner/"
                + encryptedSummonerId + "?api_key" + RIOT_API_KEY;



        return null;
    }

    /**
     * 소환사 명을 입력받아 암호화된 아이디를 얻어내는 메서드
     * @param lolNickname - 소환사명
     * @return - 암호화된 아이디
     */
    public String getEncryptedSummonerId(String lolNickname) {

        // 요청 URI
        String summonerV4RequestUri = RIOT_URI + "/summoner/v4/summoners/by-name/"
                + lolNickname + "?api_key=" + RIOT_API_KEY;

        // 요청 헤더 설정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        httpHeaders.add("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        httpHeaders.add("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.add("Origin", "https://developer.riotgames.com");

        // 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerV4DTO> responseEntity = restTemplate.exchange(
                summonerV4RequestUri,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                SummonerV4DTO.class
        );

        SummonerV4DTO responseMap = responseEntity.getBody();

        if (responseMap == null) {
            throw new NoSummonerException("해당하는 소환사 명이 존재하지 않습니다.");
        }

        return responseMap.getId();
    }

}
