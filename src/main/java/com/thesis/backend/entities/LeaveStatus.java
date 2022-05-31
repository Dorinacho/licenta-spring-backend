package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thesis.backend.entities.enums.ELeaveStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leave_status")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_status_id")
    private Integer leaveStatusId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ELeaveStatus name;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = Leave.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "status")
    private List<Leave> leaveRequests = new ArrayList<>();

    public Integer getLeaveStatusId() {
        return leaveStatusId;
    }

    public void setLeaveStatusId(Integer leaveStatusId) {
        this.leaveStatusId = leaveStatusId;
    }

    public ELeaveStatus getName() {
        return name;
    }

    public void setName(ELeaveStatus name) {
        this.name = name;
    }

    public List<Leave> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<Leave> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
}
