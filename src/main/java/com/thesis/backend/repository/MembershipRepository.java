package com.thesis.backend.repository;

import com.thesis.backend.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    @Override
    List<Membership> findAll();
}
