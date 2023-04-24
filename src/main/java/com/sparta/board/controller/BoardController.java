package com.sparta.board.controller;

import com.sparta.board.dto.boardDto.BoardRequestDto;
import com.sparta.board.dto.boardDto.BoardResponseDto;
import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/posts")  // 게시판 전체 목록 보기
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/posts") // 게시판 글 생성
    public BoardResponseDto writeBoard(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.writeBoard(boardRequestDto);
    }

    @GetMapping("/posts/{id}") // 게시글 조회
    public BoardResponseDto viewBoard(@PathVariable Long id){
        return boardService.viewBoard(id);
    }

    @PutMapping("/posts/{id}") // 게시글 수정
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.updateBoard(id, boardRequestDto);
    }
    @DeleteMapping("/posts/{id}") //게시글 삭제
    public MsgResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.deleteBoard(id, boardRequestDto);
    }


}
