package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.repository.AccuseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccuseService {
    //신고내역(ADMIN)
    private final AccuseRepository accuseRepository;
    public List<AccuseListResponseDTO> getAccuseListByAdmin() {
        List<AccuseListResponseDTO> accuseListDTO = accuseRepository.findAll()
                .stream()
                .map(AccuseListResponseDTO::new)
                .collect(toList());

        return accuseListDTO;
    }

    //금일 작성 게시물 (ADMIN)
    public List<AccuseListResponseDTO> todayAccuseByAdmin(){
        List<AccuseListResponseDTO> accuseListByAdmin = getAccuseListByAdmin();
        List<AccuseListResponseDTO> todayAccuseList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (AccuseListResponseDTO accuseByAdminResponseDTO : accuseListByAdmin) {
            System.out.println("accuseByAdminResponseDTO = " + accuseByAdminResponseDTO);
            LocalDate writtenDate = accuseByAdminResponseDTO.getAccuseWrittenDate();
            if(writtenDate!=null&&writtenDate.equals(currentDate)){
                todayAccuseList.add(accuseByAdminResponseDTO);
            }
        }
        System.out.println("todayAccuseList = " + todayAccuseList);
        return todayAccuseList;


    }
}
