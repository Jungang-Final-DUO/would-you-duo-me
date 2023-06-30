package site.woulduduo.repository;

import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.response.user.UserProfileResponseDTO;

import java.util.List;

public interface UserQueryDSLRepositoryCustom {

    List<UserProfileResponseDTO> getUserProfileList(UserSearchType userSearchType);

    int modifyProfileCard(UserCommentRequestDTO dto);
}
