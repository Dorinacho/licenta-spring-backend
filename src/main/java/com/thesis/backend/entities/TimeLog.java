package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "time_logs")
public class TimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_log_id")
    private int timeLogId;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "employeeId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @Column(name = "date")
    private String date;

    @Column(name = "start_time")
//    private LocalTime startTime;
    private String startTime;

    @Column(name = "end_time")
//    private LocalTime endTime;
    private String endTime;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "observations")
    private String observations;

    @Column(name = "hours")
    private float hours;

    public int getTimeLogId() {
        return timeLogId;
    }

    public void setTimeLogId(int timeLogId) {
        this.timeLogId = timeLogId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }
}
