package site.woulduduo.repository;

import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserProfilesResponseDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserQueryDSLRepositoryCustom {

    List<UserProfilesResponseDTO> getUserProfileList(UserSearchType userSearchType/*, HttpSession session*/);
}
