package com.sparta.board.entity;

import com.sparta.board.dto.commentDto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int commentLike;

    @ManyToOne
    @JoinColumn
    private Members members;

    public Comment(CommentRequestDto commentRequestDto, Members members) {
        this.boardId = commentRequestDto.getBoardId();
        this.content = commentRequestDto.getContent();
        this.members = members;
        this.commentLike = 0;
    }

    public void update(CommentRequestDto commentRequestDto, Members members) {
        this.content = commentRequestDto.getContent();
        this.members = members;
    }

    public void like() {
        this.commentLike += 1;
    }

    public void unlike() {
        this.commentLike -= 1;
    }
}
