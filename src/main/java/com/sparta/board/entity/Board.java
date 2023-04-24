package com.sparta.board.entity;

import com.sparta.board.dto.boardDto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public Board(BoardRequestDto boardRequestDto){
        this.username = boardRequestDto.getUsername();
        this.password = boardRequestDto.getPassword();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void update(BoardRequestDto boardRequestDto){
        this.username = boardRequestDto.getUsername();
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }



}
