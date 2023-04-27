package com.sparta.board.service;

import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.dto.commentDto.CommentRequestDto;
import com.sparta.board.dto.commentDto.CommentResponseDto;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.UserRoleEnum;
import com.sparta.board.entity.Members;
import com.sparta.board.exception.CustomException;
import com.sparta.board.exception.ErrorCode;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public CommentResponseDto writeComment(CommentRequestDto commentRequestDto, Members members){
        boardRepository.findById(commentRequestDto.getBoardId()).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto, members));
        return new CommentResponseDto(comment);

    }

    @Transactional
    public  CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, Members members){
        UserRoleEnum role = members.getRole();

        Comment comment;
        comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );

        if(role != UserRoleEnum.ADMIN){
            comment = commentRepository.findByIdAndMembers_username(id, members.getUsername()).orElseThrow(
                    () -> new CustomException(ErrorCode.CANNOT_UPDATE)
            );
        }

        comment.update(commentRequestDto, members);
        return new CommentResponseDto(comment);

    }

    @Transactional
    public MsgResponseDto deleteComment(Long id, Members members){

        UserRoleEnum role = members.getRole();

        Comment comment= commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );

        if(role != UserRoleEnum.ADMIN){
            comment = commentRepository.findByIdAndMembers_username(id, members.getUsername()).orElseThrow(
                    () -> new CustomException(ErrorCode.CANNOT_UPDATE)
            );
        }

        likeRepository.deleteAllByCommentId(id);
        commentRepository.deleteById(id);
        return new MsgResponseDto("완료", 200);

}
}
