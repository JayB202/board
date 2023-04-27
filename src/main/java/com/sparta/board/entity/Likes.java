package com.sparta.board.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Likes {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn
    private Members members;

    @ManyToOne
    @JoinColumn
    private Board board;

    @ManyToOne
    @JoinColumn
    private Comment comment;



    public Likes(Members members, Board board){
        this.members = members;
        this.board = board;
    }

    public Likes(Members members, Board board, Comment comment){
        this.members = members;
        this.board = board;
        this.comment = comment;
    }
}
