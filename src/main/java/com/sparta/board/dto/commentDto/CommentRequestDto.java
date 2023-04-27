package com.sparta.board.dto.commentDto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long id;
    private String content;
    private String username;
    private Long boardId;
}