package com.sparta.board.service;


import com.sparta.board.dto.boardDto.BoardRequestDto;
import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.entity.Board;
import com.sparta.board.dto.boardDto.BoardResponseDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public List<BoardResponseDto> getBoards(){
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDto boardResponseDto1 = new BoardResponseDto(board);
            boardResponseDto.add(boardResponseDto1);
        }
        return boardResponseDto;
    }

    @Transactional
    public BoardResponseDto writeBoard(BoardRequestDto boardRequestDto){
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto viewBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시물 입니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
                );
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        if(boardRequestDto.getPassword().equals(board.getPassword())) {
            board.update(boardRequestDto);
        }
        return boardResponseDto;

    }

    @Transactional
    public MsgResponseDto deleteBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다")
        );
        MsgResponseDto msgResponseDto = new MsgResponseDto();
        if (boardRequestDto.getPassword().equals(board.getPassword())) {
            boardRepository.deleteById(id);
            msgResponseDto.setMsg("success");
        } else {
            msgResponseDto.setMsg("fail");
        }
        return msgResponseDto;

    }
}
