package site.woulduduo.repository;

import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.response.user.UserProfileResponseDTO;

import java.util.List;

public interface UserQueryDSLRepositoryCustom {

    List<UserProfileResponseDTO> getUserProfileList(UserSearchType userSearchType, HttpSession session);

    Long modifyProfileCard(UserCommentRequestDTO dto, HttpSession session);
    List<UserProfileResponseDTO> getUserProfileList(UserSearchType userSearchType);

    Long deleteProfileCard(UserCommentRequestDTO dto, HttpSession session);

    int modifyProfileCard(UserCommentRequestDTO dto);
}