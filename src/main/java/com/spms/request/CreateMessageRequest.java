package com.spms.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageRequest {

    private Long senderId;

    private Long projectId;

    private String content;
}
