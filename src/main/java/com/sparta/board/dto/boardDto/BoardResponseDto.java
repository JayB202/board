package com.sparta.board.dto.boardDto;

import com.sparta.board.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.*;
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getUsername();
        this.created = board.getCreatedAt();
        this.modified = board.getModifiedAt();

    }
}
