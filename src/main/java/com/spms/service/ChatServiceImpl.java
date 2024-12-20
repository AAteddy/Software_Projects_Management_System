package com.spms.service;

import com.spms.model.Chat;
import com.spms.repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepo.save(chat);
    }
}
