package com.spms.controller;


import com.spms.model.Comment;
import com.spms.model.User;
import com.spms.request.CreateCommentRequest;
import com.spms.response.ApiMessageResponse;
import com.spms.service.CommentService;
import com.spms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest request,
            @RequestHeader("Authorization") String token
            ) throws Exception {

        User user = userService.findUserProfileByJwt(token);

        Comment createdComment = commentService.createComment(
                request.getIssueId(),
                user.getId(),
                request.getContent());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiMessageResponse> removeComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String token
    ) throws Exception {

        User user = userService.findUserProfileByJwt(token);

        commentService.deleteComment(commentId, user.getId());

        ApiMessageResponse response = new ApiMessageResponse();
        response.setMessage("Comment deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
