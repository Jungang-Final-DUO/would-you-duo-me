package site.woulduduo.service;

import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserDetailByAdminResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;

public class UserService {

    public ListResponseDTO<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type){

        return null;
    }

    public UserDetailByAdminResponseDTO getUserDetailByAdmin(String userAccount){

        return null;
    }

    public boolean increaseUserPoint(UserModifyRequestDTO dto){

        return false;
    }

    public boolean changeUserPoint(UserModifyRequestDTO dto){

        return false;
    }



}
