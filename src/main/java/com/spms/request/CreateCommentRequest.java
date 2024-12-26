package com.spms.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {

    private Long issueId;

    private String content;
}
