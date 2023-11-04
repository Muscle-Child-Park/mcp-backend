package com.park.muscle.core.member.dto.request;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.member.domain.Role;
import com.park.muscle.global.enumerate.SocialType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    public Member toEntity;

    @NotBlank(message = "소셜 UID는 반드시 존재해야 합니다.")
    private String socialId;

    @NotBlank(message = "소셜 타입은 반드시 존재해야 합니다.")
    private String socialType;

    @NotBlank(message = "이름은 반드시 존재해야 합니다.")
    private String name;

    public String createUserNumber() {
        return String.format("%s#%s", SocialType.KAKAO, this.getSocialId());
    }

    private LoginRequest() {
    }

    public Member toEntity() {
        return Member.builder()
                .socialId(createUserNumber())
                .socialType(SocialType.findType(this.socialType))
                .name(Name.from(this.name))
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
