package com.spms.service;


import com.spms.model.Invitation;
import com.spms.repository.InvitationRepo;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepo invitationRepo;

    private final EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepo.save(invitation);

        //frontend page url
        String invitationLink =
                "http://localhost:5173/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);

    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {

        Invitation invitation = invitationRepo.findByToken(token);
        if (invitation == null)
            throw new Exception("Invalid Invitation Token");

        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {

        Invitation invitation = invitationRepo.findByEmail(userEmail);

        return invitation.getToken();
    }


}
