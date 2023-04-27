package com.sparta.board.dto.boardDto;

import com.sparta.board.dto.commentDto.CommentResponseDto;
import com.sparta.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.*;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String username;
    private List<CommentResponseDto> commentList;
    private int like;

    public BoardResponseDto(Board board, List<CommentResponseDto> commentList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.created = board.getCreatedAt();
        this.modified = board.getModifiedAt();
        this.username = board.getUsername();
        this.commentList = commentList;
        this.like = board.getLikes();

    }
}
