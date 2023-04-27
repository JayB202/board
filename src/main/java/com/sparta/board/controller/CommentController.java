package com.sparta.board.controller;


import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.dto.commentDto.CommentRequestDto;
import com.sparta.board.dto.commentDto.CommentResponseDto;
import com.sparta.board.security.UserDetailsImplement;
import com.sparta.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentResponseDto writeComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return commentService.writeComment(commentRequestDto, userDetailsImplement.getUsers());

    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto,  @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return commentService.updateComment(id, commentRequestDto, userDetailsImplement.getUsers());
    }

    @DeleteMapping("/{id}")
    public MsgResponseDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return commentService.deleteComment(id, userDetailsImplement.getUsers());
    }
}
