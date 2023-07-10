package com.park.muscle.memberinfo.query;

import com.park.muscle.memberinfo.command.domain.Age;
import com.park.muscle.memberinfo.command.domain.Height;
import com.park.muscle.memberinfo.command.domain.InBody;
import com.park.muscle.memberinfo.command.domain.Nickname;
import com.park.muscle.memberinfo.command.domain.Weight;

public class MemberInfoRequest {

    private Age age;
    private Height height;
    private Weight weight;
    private InBody inBody;
    private Nickname nickname;

    public Age getAge() {
        return age;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public InBody getInBody() {
        return inBody;
    }

    public Nickname getNickname() {
        return nickname;
    }
}

