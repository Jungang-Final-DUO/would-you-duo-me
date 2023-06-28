package site.woulduduo.repository;

import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.response.user.UserProfileResponseDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserQueryDSLRepositoryCustom {

    List<UserProfileResponseDTO> getUserProfileList(UserSearchType userSearchType/*, HttpSession session*/);

    int modifyProfileCard(UserCommentRequestDTO dto/* , HttpSession session*/);
}
