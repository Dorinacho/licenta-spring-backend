package com.thesis.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainers")
public class Trainer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainer_id")
    private int trainerId;

    @ManyToOne(targetEntity = Gym.class)
    @JoinColumn(name = "fk_gym_id")
    private Gym gym;


    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    @Column(name = "fk_user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String phone;

    @Column(name = "email")
    private String email;
}
