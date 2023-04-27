package com.sparta.board.service;

import static com.sparta.board.exception.ErrorCode.*;
import com.sparta.board.dto.boardDto.BoardRequestDto;
import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.dto.commentDto.CommentResponseDto;
import com.sparta.board.entity.Comment;
import com.sparta.board.exception.CustomException;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Members;
import com.sparta.board.entity.UserRoleEnum;
import com.sparta.board.dto.boardDto.BoardResponseDto;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.LikeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;


    @Transactional
    public List<BoardResponseDto> viewBoardList(){
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();
        for (Board board : boardList) {
            boardResponseDto.add(new BoardResponseDto(board, getCommentList(board.getId())));
        }
        return boardResponseDto;
    }

    @Transactional
    public BoardResponseDto writeBoard(BoardRequestDto boardRequestDto, Members members){
        Board board = new Board(boardRequestDto, members.getUsername());
        boardRepository.save(board);
        List<CommentResponseDto> commentList = new ArrayList<>();
        return new BoardResponseDto(board, commentList);
    }

    @Transactional(readOnly = true)
    public BoardResponseDto viewBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new CustomException(BOARD_NOT_FOUND)
        );
        return new BoardResponseDto(board, getCommentList(id));
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, Members members) {
        UserRoleEnum userRoleEnum = members.getRole();
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(BOARD_NOT_FOUND )
        );
        if(userRoleEnum != UserRoleEnum.ADMIN){
            board = boardRepository.findByIdAndUsername(id, members.getUsername()).orElseThrow(
                    () -> new CustomException(CANNOT_UPDATE)
            );
        }
        board.update((boardRequestDto));
        return new BoardResponseDto(board, getCommentList(id));

    }

    @Transactional
    public MsgResponseDto deleteBoard(Long id, Members members) {
        UserRoleEnum role = members.getRole();
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(BOARD_NOT_FOUND)
        );
        if(role != UserRoleEnum.ADMIN){
            board = boardRepository.findByIdAndUsername(id, members.getUsername()).orElseThrow(
                    () -> new CustomException(CANNOT_DELETE)
           );
        }
        List<Comment> commentList= commentRepository.findAllByBoardIdOrderByCreatedAtDesc(id);
        List<Long> commentIdList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentIdList.add(comment.getId());
        }
        for (Long commentId : commentIdList) {
            likeRepository.deleteAllByCommentId(commentId);
        }

        likeRepository.deleteAllByBoardId(id);
        commentRepository.deleteAllByBoardId(id);
        boardRepository.deleteById(id);
        return new MsgResponseDto("완료", 200);


    }


    private List<CommentResponseDto> getCommentList(Long postId) {
        // 게시글에 달린 댓글 찾아서 작성일 기준 내림차순 정렬
        List<Comment> commentList = commentRepository.findAllByBoardIdOrderByCreatedAtDesc(postId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }
}
