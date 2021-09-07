package com.example.parksproject.service;

import com.example.parksproject.domain.User;
import com.example.parksproject.payload.InfoResponse;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> modifyUserInfo(UserPrincipal userPrincipal, InfoResponse infoResponse) {
        User user = userRepository.findById(userPrincipal.getId()).get();
        User modifyUser = User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .emailVerified(user.getEmailVerified())
                .password(user.getPassword())
                .authProvider(user.getAuthProvider())
                .providerId(user.getProviderId())
                .bio(infoResponse.getBio())
                .occupation(infoResponse.getOccupation())
                .location(infoResponse.getLocation()).build();
        userRepository.save(modifyUser);

        return ResponseEntity.ok("수정 완료");

    }
}
