package com.sparta.board.entity;

import com.sparta.board.dto.boardDto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likes;


    @OneToMany
    private List<Comment> commentList = new ArrayList<>();

    public Board(BoardRequestDto boardRequestDto, String username) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.username = username;
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

    public void like() {
        this.likes += 1;
    }

    public void unlike() {
        this.likes -= 1;
    }

}
