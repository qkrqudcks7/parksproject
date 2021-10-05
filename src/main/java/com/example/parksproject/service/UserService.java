package com.example.parksproject.service;

import com.example.parksproject.domain.User;
import com.example.parksproject.payload.CreatedStudyResponse;
import com.example.parksproject.payload.InfoResponse;
import com.example.parksproject.payload.MyStudyResponse;
import com.example.parksproject.payload.UserResponse;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> getMyInfo(Long id) {
        User user = userRepository.findById(id).get();
        UserResponse userResponse = new UserResponse(user.getId(), user.getName(),user.getEmail(),user.getImageUrl(),user.getRole(),user.getEmailVerified(),user.getAuthProvider(),user.getProviderId(),user.getBio(),user.getOccupation(),user.getLocation());
        return ResponseEntity.ok(userResponse);
    }

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

    public ResponseEntity<?> findMyStudy(Long id) {
        User user = userRepository.findById(id).get();

        List<MyStudyResponse> collect = user.getApplyStudies().stream().map(applyStudy -> new MyStudyResponse(applyStudy.getStudy().getId(), applyStudy.getApplyState().toString(), applyStudy.getStudy().getTitle(), applyStudy.getStudy().getImage(), applyStudy.getStudy().isRecruiting(), applyStudy.getStudy().isPublished(), applyStudy.getStudy().isClosed())).collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<?> getCreatedStudy(Long id) {
        User user = userRepository.findById(id).get();
        List<CreatedStudyResponse> collect = user.getManagers().stream().map(manager -> new CreatedStudyResponse(manager.getStudy().getId(), manager.getStudy().getImage(), manager.getStudy().getTitle(), manager.getStudy().getMaxMember(), manager.getStudy().getApplies())).collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
