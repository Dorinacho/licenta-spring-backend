package com.thesis.backend.entities.leave;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thesis.backend.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave")
@Entity
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private int leaveId;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "employeeId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

//    @Column(name = "leave_type")
//    private String leaveType;

    @Column(name = "reason")
    private String reason;

//    @Column(name = "status")
//    private String status;

    @JsonIdentityInfo(scope = LeaveStatus.class, generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "leaveStatusId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = LeaveStatus.class)
    @JoinColumn(name = "status")
    private LeaveStatus status;

    @JsonIdentityInfo(scope = LeaveType.class,generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "leaveTypeId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(targetEntity = LeaveType.class)
    @JoinColumn(name = "leave_type")
    private LeaveType leaveType;

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int timeLogId) {
        this.leaveId = timeLogId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}