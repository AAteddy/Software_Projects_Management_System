package com.spms.repository;

import com.spms.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvitationRepo extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);

}
