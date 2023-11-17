package com.park.muscle.core.trainer.application;

import static com.park.muscle.core.trainer.dto.TrainerResponseDto.LoginResponse;
import static com.park.muscle.core.trainer.dto.TrainerResponseDto.SignUpResponse;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.exception.MemberNotFoundException;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.TicketDto.TrainerTicketResponse;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.domain.TrainerRepository;
import com.park.muscle.core.trainer.dto.TrainerRequestDto.LoginRequest;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.global.enumerate.SocialType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        return LoginResponse.fromEntity(accessToken, refreshToken, new SignUpResponse(trainer));
    }

    public Member getMemberInfo(final Long trainerId, final Long memberId) {
        return null;
    }

    public Trainer getTrainerById(final Long trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public List<TrainerTicketResponse> getTrainerTickets(final List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> new TrainerTicketResponse(
                        ticket.getTrainer(),
                        ticket.getMember(),
                        ticket.getTotalQuantity(),
                        ticket.isAccepted()))
                .collect(Collectors.toList());
    }
}
