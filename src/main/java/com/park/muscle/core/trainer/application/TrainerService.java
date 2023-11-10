package com.park.muscle.core.trainer.application;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.domain.TrainerRepository;
import com.park.muscle.core.trainer.dto.TrainerDto;
import com.park.muscle.core.trainer.dto.TrainerDto.LoginRequest;
import com.park.muscle.core.trainer.dto.TrainerDto.LoginResponse;
import com.park.muscle.core.trainer.dto.TrainerDto.SignUpRequest;
import com.park.muscle.core.trainer.dto.TrainerDto.SignUpResponse;
import com.park.muscle.core.trainer.dto.request.ClassRegistrationRequest;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.global.enumerate.SocialType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {

    private final JwtTokenProvider jwtTokenProvider;
    private final TrainerRepository trainerRepository;
    private final UniqueTagRepository uniqueTagRepository;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        String userNumber = String.format("%s#%s", SocialType.KAKAO, loginRequest.getSocialId());
        Optional<Trainer> loginTrainer = trainerRepository.findByUsername(userNumber);

        if (loginTrainer.isPresent()) {
            Trainer trainer = loginTrainer.get();
            updateTrainerInfo(loginRequest, trainer);
            return createSingUpResult(trainer);
        }

        Trainer trainer = singUp(loginRequest);
        return createSingUpResult(trainer);
    }

    private Trainer singUp(final LoginRequest loginRequest) {
        Trainer trainer = loginRequest.toEntity();
        trainer.setUniqueTag(new UniqueTag());
        uniqueTagRepository.save(trainer.getUniqueTag());
        return trainerRepository.save(trainer);
    }

    private void updateTrainerInfo(final LoginRequest loginRequest, final Trainer trainer) {
        trainer.updateName(loginRequest.getName());
    }

    private LoginResponse createSingUpResult(final Trainer trainer) {
        String accessToken = jwtTokenProvider.createAccessToken(trainer.getId(), trainer.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(trainer.getId());

        jwtTokenProvider.saveTrainerTokenInRedis(trainer, refreshToken);
        return new LoginResponse(accessToken, refreshToken, new SignUpResponse(trainer));
    }

    public boolean approveMemberRegistration(final Long trainerId, final Long memberId, final ClassRegistrationRequest classRegistrationRequest) {
        return false;
    }

    public Member getMemberInfo(final Long trainerId, final Long memberId) {
        return null;
    }
}
