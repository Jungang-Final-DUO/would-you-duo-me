package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.entity.User;
import site.woulduduo.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;



}
