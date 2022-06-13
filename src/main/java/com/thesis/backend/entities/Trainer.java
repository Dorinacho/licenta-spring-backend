package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private int trainerId;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "gymId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Gym.class)
    @JoinColumn(name = "fk_gym_id")
    private Gym gym;

//    @JsonManagedReference
//    @JsonIgnore
//    @OneToMany(targetEntity = Client.class, cascade = CascadeType.MERGE,
//            orphanRemoval = true, mappedBy = "membership")
//    private List<Client> clients = new ArrayList<>();

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = Training.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "principalTrainer")
    private List<Training> principalTrainings = new ArrayList<>();

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = Training.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "secondaryTrainer")
    private List<Training> secondaryTrainings = new ArrayList<>();

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

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
