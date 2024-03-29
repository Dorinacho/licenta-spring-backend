package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.*;
import com.thesis.backend.entities.leave.Leave;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "gymId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Gym.class)
    @JoinColumn(name = "fk_gym_id")
    private Gym gym;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = TimeLog.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "employee")
    private List<TimeLog> timeLogs = new ArrayList<>();

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = Leave.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "employee")
    private List<Leave> leaves = new ArrayList<>();

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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Gym getGym() {
        return gym;
    }

    @JsonProperty("gym")
    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public List<TimeLog> getTimeLogs() {
        return timeLogs;
    }

    public void setTimeLogs(List<TimeLog> timeLogs) {
        this.timeLogs = timeLogs;
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
