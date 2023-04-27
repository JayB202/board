package com.sparta.board.service;

import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Likes;
import com.sparta.board.entity.Members;
import com.sparta.board.exception.CustomException;
import com.sparta.board.exception.ErrorCode;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    private boolean isBoardLike(Members members, Board board) {
        Optional<Likes> like = likeRepository.findByMembersIdAndBoardId(members.getId(), board.getId());
        if (like.isPresent()) {
            return true;
        }
        return false;
    }

    private boolean isCommentLike(Members members, Board board, Comment comment) {
        Optional<Likes> like = likeRepository.findByMembersIdAndBoardIdAndCommentId(members.getId(), board.getId(), comment.getId());
        if (like.isPresent()) {
            return true;
        }
        return false;
    }

    @Transactional
    public MsgResponseDto boardLike(Long boardId, Members members) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        isBoardLike(members, board);
        if (isBoardLike(members, board)) {
            board.like();
            likeRepository.deleteByMembersIdAndBoardId(members.getId(), boardId);
            return new MsgResponseDto("좋아요 취소", 200);
        } else {
            board.unlike();
            Likes likes = new Likes(members, board);
            likeRepository.save(likes);
            return new MsgResponseDto("좋아요", 200);

        }
    }


    @Transactional
    public MsgResponseDto commentLike(Long commentId, Long boardId, Members members) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );

        isCommentLike(members, board, comment);
        if (isCommentLike(members, board, comment)) {
            comment.like();
            likeRepository.deleteByMembersIdAndBoardIdAndCommentId(members.getId(), boardId, commentId);
            return new MsgResponseDto("좋아요 취소", 200);
        } else {
            comment.unlike();
            Likes likes = new Likes(members, board, comment);
            likeRepository.save(likes);
            return new MsgResponseDto("좋아요", 200);

        }


    }
}
