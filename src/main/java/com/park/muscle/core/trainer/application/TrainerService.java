package com.park.muscle.core.trainer.application;

import static com.park.muscle.core.trainer.dto.TrainerResponse.SignUpResponse;
import static com.park.muscle.core.trainer.dto.TrainerResponse.TokenResponse;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import com.park.muscle.core.jwt.dto.ReIssueTokenDto;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.reservation.application.ReservationService;
import com.park.muscle.core.reservation.dto.ReservationResponse.ReservationInfoResponse;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.ticket.dto.response.TicketResponse.PendingMemberNameResponse;
import com.park.muscle.core.ticket.dto.response.TicketResponse.TicketTrainerResponse;
import com.park.muscle.core.trainer.domain.DayOff;
import com.park.muscle.core.trainer.domain.DayOffRepository;
import com.park.muscle.core.trainer.domain.Gym;
import com.park.muscle.core.trainer.domain.GymRepository;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.trainer.domain.TrainerRepository;
import com.park.muscle.core.trainer.dto.TrainerRequest.DayOffRequest;
import com.park.muscle.core.trainer.dto.TrainerRequest.LoginRequest;
import com.park.muscle.core.trainer.dto.TrainerResponse.LoginResponse;
import com.park.muscle.core.trainer.exception.TrainerNotFoundException;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.global.enumerate.SocialType;
import com.park.muscle.global.util.CookieUtil;
import com.park.muscle.global.util.HttpHeaderUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {
    private final JwtTokenProvider jwtTokenProvider;
    private final TrainerRepository trainerRepository;
    private final ReservationService reservationService;
    private final UniqueTagRepository uniqueTagRepository;
    private final GymRepository gymRepository;
    private final DayOffRepository dayOffRepository;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        String userNumber = String.format("%s#%s", SocialType.KAKAO, loginRequest.getSocialId());
        Optional<Trainer> loginTrainer = trainerRepository.findByUsername(userNumber);

        Trainer trainer = loginTrainer.orElseGet(() -> singUp(loginRequest));
        TokenResponse tokenResponse = createTokenResponse(trainer);
        HttpHeaders headers = setCookieAndHeader(tokenResponse);
        SignUpResponse signUpResponse = SignUpResponse.fromEntity(trainer);
        return LoginResponse.fromEntity(headers, signUpResponse);
    }

    private TokenResponse createTokenResponse(Trainer trainer) {
        if (!trainer.isNew()) {
            return createSingUpResult(trainer);
        }
        return createLoginResult(trainer);
    }

    private TokenResponse createLoginResult(final Trainer trainer) {
        String accessToken = jwtTokenProvider.createAccessToken(trainer.getUniqueTagId(), trainer.getRole());
        String refreshToken = jwtTokenProvider.getValidRefreshToken(trainer.getUniqueTagId());
        log.info("refresh token = {}", refreshToken);
        jwtTokenProvider.saveMemberTokenInRedis(trainer, refreshToken);
        return TokenResponse.fromEntity(accessToken, refreshToken);
    }

    private Trainer singUp(final LoginRequest loginRequest) {
        Trainer trainer = loginRequest.toEntity();
        uniqueTagRepository.save(trainer.getUniqueTag());
        return trainerRepository.save(trainer);
    }

    private TokenResponse createSingUpResult(final Trainer trainer) {
        String accessToken = jwtTokenProvider.createAccessToken(trainer.getId(), trainer.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(trainer.getId());

        jwtTokenProvider.saveTrainerTokenInRedis(trainer, refreshToken);
        return TokenResponse.fromEntity(accessToken, refreshToken);
    }

    private void updateTrainerInfo(final LoginRequest loginRequest, final Trainer trainer) {
        trainer.updateName(loginRequest.getName());
    }

    public Trainer findTrainerById(final Long trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(TrainerNotFoundException::new);
    }

    public List<TicketTrainerResponse> getTrainerTickets(final List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> {
                    String trainer = ticket.getTrainer().getUniqueTag().formattedId();
                    String member = ticket.getMember().getUniqueTag().formattedId();
                    int totalQuantity = ticket.getTotalQuantity();
                    boolean accepted = ticket.isAccepted();
                    return TicketTrainerResponse.fromEntity(trainer, member, totalQuantity, accepted);
                })
                .collect(Collectors.toList());
    }

    public List<ReservationInfoResponse> getReserveMembers(final List<Ticket> tickets) {
        return reservationService.findReservationsInfo(tickets);
    }

    public List<Member> findPendingMembers(final List<Ticket> tickets) {
        return tickets.stream()
                .filter(ticket -> !ticket.isAccepted())
                .map(Ticket::getMember)
                .collect(Collectors.toList());
    }

    public PendingMemberNameResponse getPendingMembers(final List<Member> pendingMembers) {
        List<String> pendingNames = pendingMembers.stream()
                .map(member -> member.getName().getValue())
                .collect(Collectors.toList());
        return PendingMemberNameResponse.fromEntity(pendingNames);
    }

    @Transactional
    public void deleteTrainerAccount(final Long trainerId) {
        Trainer trainer = findTrainerById(trainerId);
        trainerRepository.delete(trainer);
    }

    @Transactional
    public void saveGym(final Trainer trainer, final Gym gym) {
        gymRepository.save(gym);
        trainerRepository.save(trainer);
    }

    @Transactional
    public void addTrainerOff(final Trainer trainer, final List<DayOffRequest> dayOffRequest) {
        List<DayOff> dayOffs = dayOffRequest.stream()
                .map(dayOff -> {
                    DayOff newDayOff = dayOff.toEntity();
                    trainer.addDayOffs(newDayOff);
                    return newDayOff;
                })
                .collect(Collectors.toList());
        dayOffRepository.saveAll(dayOffs);
    }

    public static HttpHeaders setCookieAndHeader(ReIssueTokenDto reIssueTokenDto) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, reIssueTokenDto.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, reIssueTokenDto.getAccessToken());
        return headers;
    }

    public static HttpHeaders setCookieAndHeader(TokenResponse tokenResponse) {
        HttpHeaders headers = new HttpHeaders();
        CookieUtil.setRefreshCookie(headers, tokenResponse.getRefreshToken());
        HttpHeaderUtil.setAccessToken(headers, tokenResponse.getAccessToken());
        return headers;
    }
}
