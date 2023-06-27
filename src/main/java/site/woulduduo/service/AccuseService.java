package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.accuse.UserAccuseRequestDTO;
import site.woulduduo.dto.response.accuse.AccuseListResponseDTO;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.repository.AccuseRepository;
import site.woulduduo.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccuseService {

    private final AccuseRepository accuseRepository;
    private final UserRepository userRepository;


    //신고내역(ADMIN)

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

    //경고 데이터 전달
    public boolean accuseUser(UserAccuseRequestDTO dto){

        String userAccount = dto.getUserAccount();
        User byUserAccount = userRepository.findByUserAccount(userAccount);

        Accuse entity = dto.toEntity();
        entity.setUser(byUserAccount);

        String accuseType = entity.getAccuseType();
        System.out.println("accuseType = " + accuseType);

        String accuseEtc = entity.getAccuseEtc();
        System.out.println("accuseEtc = " + accuseEtc);

        if(accuseEtc!=null||accuseType!=null) {
            Accuse save = accuseRepository.save(entity);
            System.out.println("save = " + save);
            return true;
        }
        return false;
    }
}
