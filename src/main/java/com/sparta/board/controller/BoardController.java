package com.sparta.board.controller;

import com.sparta.board.dto.boardDto.BoardRequestDto;
import com.sparta.board.dto.boardDto.BoardResponseDto;
import com.sparta.board.dto.MsgResponseDto;
import com.sparta.board.security.UserDetailsImplement;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public List<BoardResponseDto> viewBoardList(){
        return boardService.viewBoardList();
    }

    @PostMapping("/")
    public BoardResponseDto writeBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return boardService.writeBoard(boardRequestDto, userDetailsImplement.getUsers());
    }

    @GetMapping("/{id}")
    public BoardResponseDto viewBoard(@PathVariable Long id){
        return boardService.viewBoard(id);
    }

    @PutMapping("/{id}") // 게시글 수정
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return boardService.updateBoard(id, boardRequestDto, userDetailsImplement.getUsers());
    }
    @DeleteMapping("/{id}") //게시글 삭제
    public MsgResponseDto deleteBoard(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImplement userDetailsImplement){
        return boardService.deleteBoard(id, userDetailsImplement.getUsers());
    }


}
