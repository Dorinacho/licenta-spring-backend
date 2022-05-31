package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thesis.backend.entities.enums.ELeaveType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leave_type")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Integer leaveTypeId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ELeaveType name;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(targetEntity = Leave.class, cascade = CascadeType.MERGE,
            orphanRemoval = true, mappedBy = "leaveType")
    private List<Leave> leaveRequests = new ArrayList<>();

    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public ELeaveType getName() {
        return name;
    }

    public void setName(ELeaveType name) {
        this.name = name;
    }

    public List<Leave> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<Leave> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
}
