package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserDetailByAdminResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.repository.BoardRepository;
import site.woulduduo.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public ListResponseDTO<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type){

        List<User> all = userRepository.findAll();
            all.forEach(user -> {

                //userPoint 찾기
                Long byUserCurrentPoint = userRepository.findByUserCurrentPoint(user.getUserAccount());


                UsersByAdminResponseDTO dto
                        =new UsersByAdminResponseDTO();
                dto.setUserAccount(user.getUserAccount());
                dto.setGender(user.getUserGender().toString());
//                dto.setBoardCount();
//                dto.setReplyCount();
//                dto.setReportCount();
                dto.setPoint(user.getUserCurrentPoint());
//                dto.setFollowCount();
            });
        }

        return null;
    }


//    public UserDetailByAdminResponseDTO getUserDetailByAdmin(String userAccount){
//
//        return null;
//    }
//
//    public boolean increaseUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }
//
//    public boolean changeUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }



}
