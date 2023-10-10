package com.park.muscle.core.member.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FurtherInfoRequest {

    @NotBlank(message = "이름은 반드시 입력해야 합니다.")
    private String name;

    private FurtherInfoRequest() {
    }

    public FurtherInfoRequest(String name) {
        this.name = name;
    }
}
