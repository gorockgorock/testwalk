package com.spacewalk.testwalk.controller;

import com.spacewalk.testwalk.dto.request.CommentCreateRequestDto;
import com.spacewalk.testwalk.dto.request.CommentUpdateRequestDto;
import com.spacewalk.testwalk.dto.response.CommentResponseDto;
import com.spacewalk.testwalk.service.CommentService;
import com.spacewalk.testwalk.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {


    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentCreateRequestDto dto) {
        return ResponseEntity.ok().body(commentService.createComment(postId, userDetails.getUser(), dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        CommentResponseDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByPostId() {
        List<CommentResponseDto> comments = commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @PathVariable Long id,
                                                            @RequestBody CommentUpdateRequestDto dto) {
        return ResponseEntity.ok().body(commentService.updateComment(userDetails.getUser(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        commentService.deleteComment(userDetails.getUser(), id);
        return ResponseEntity.ok().build();
    }
}
