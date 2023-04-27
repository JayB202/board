package com.sparta.board.controller;

import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.security.UserDetailsImplement;
import com.sparta.board.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{boardId}")
    public MsgResponseDto boardLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return likeService.boardLike(boardId,userDetailsImplement.getUsers());
    }



    @PostMapping("/comment/{commentId}")
    public MsgResponseDto commentLike(@PathVariable Long commentId,Long boardId, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return likeService.commentLike(commentId,boardId, userDetailsImplement.getUsers());
    }


}
