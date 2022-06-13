package com.thesis.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "memberships")
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id")
    private int membershipId;

//    @JsonManagedReference
//    @JsonIgnore
//    @OneToMany(targetEntity = Client.class, cascade = CascadeType.MERGE,
//            orphanRemoval = true, mappedBy = "membership")
//    private List<Client> clients = new ArrayList<>();

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String description;

    @Column
    private int discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<Client> clients) {
//        this.clients = clients;
//    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
