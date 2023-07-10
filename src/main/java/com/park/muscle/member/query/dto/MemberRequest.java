package com.park.muscle.member.query.dto;

import com.park.muscle.member.command.domain.Roles;

public class MemberRequest {

    private String id;
    private String nickname;
    private String age;
    private String email;
    private String providerId;
    private String provider;
    private Roles roles;


    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getProvider() {
        return provider;
    }

    public Roles getRoles() {
        return roles;
    }
}
