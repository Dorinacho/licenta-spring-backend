package com.thesis.backend.services;

import com.thesis.backend.entities.Membership;
import com.thesis.backend.repository.MembershipRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MembershipService {
    @Autowired
    MembershipRepository membershipRepository;

    public void saveMembership(Membership membership) {


        membershipRepository.save(membership);
    }
}
