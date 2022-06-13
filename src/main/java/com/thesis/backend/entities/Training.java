package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainings")
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private int trainingId;

//    @Column(name = "fk_principal_trainer")
//    private int fkPrincipalTrainer;
//
//    @Column(name = "fk_secondary_trainer")
//    private int fkSecondaryTrainer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "trainerId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Trainer.class)
    @JoinColumn(name = "fk_principal_trainer")
    private Trainer principalTrainer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "trainerId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Trainer.class)
    @JoinColumn(name = "fk_secondary_trainer")
    private Trainer secondaryTrainer;

    @Column
    private String name;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column
    private String dayOfWeek;

    @Column
    private String description;

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public Trainer getPrincipalTrainer() {
        return principalTrainer;
    }

    public void setPrincipalTrainer(Trainer principalTrainer) {
        this.principalTrainer = principalTrainer;
    }

    public Trainer getSecondaryTrainer() {
        return secondaryTrainer;
    }

    public void setSecondaryTrainer(Trainer secondaryTrainer) {
        this.secondaryTrainer = secondaryTrainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
