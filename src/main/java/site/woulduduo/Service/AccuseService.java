package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.repository.AccuseRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccuseService {

    private final AccuseRepository accuseRepository;
    public List<AccuseListResponseDTO> getAccuseListByAdmin() {
        List<AccuseListResponseDTO> accuseListDTO = accuseRepository.findAll()
                .stream()
                .map(AccuseListResponseDTO::new)
                .collect(toList());

        return accuseListDTO;
    }
}
