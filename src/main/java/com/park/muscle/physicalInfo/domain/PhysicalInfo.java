package com.park.muscle.physicalInfo.domain;

import com.park.muscle.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PhysicalInfo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "physical_info_id")
    private long id;
    private int age;
    private int height;
    private int weight;
    private String inBody;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}