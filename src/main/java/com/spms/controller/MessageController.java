package com.spms.controller;


import com.spms.model.Chat;
import com.spms.model.Message;
import com.spms.model.User;
import com.spms.request.CreateMessageRequest;
import com.spms.service.MessageService;
import com.spms.service.ProjectService;
import com.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request
            ) throws Exception {

        User user = userService.findUserById(request.getSenderId());
        if (user == null)
            throw new Exception("User not found with user Id : " + request.getSenderId());

        Chat chats = projectService.getProjectById(request.getProjectId()).getChat(); // this method should throw chatException if the chat is not found
        if (chats == null)
            throw new Exception("Chats not found");

        Message sendMessage = messageService.sendMessage(
                request.getSenderId(),
                request.getProjectId(),
                request.getContent());

        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessageByProjectId(
            @PathVariable Long projectId
    ) throws Exception {

        List<Message> messages = messageService.getMessagesByProjectId(projectId);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
