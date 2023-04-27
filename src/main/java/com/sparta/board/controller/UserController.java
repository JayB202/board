package com.sparta.board.controller;
import com.sparta.board .dto.userDto.SignInRequestDto;
import com.sparta.board.dto.userDto.UserMsgResponseDto;
import com.sparta.board.dto.userDto.SignUpRequestDto;
import com.sparta.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserMsgResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new UserMsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }
    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<UserMsgResponseDto> login(@RequestBody SignInRequestDto signInRequestDto, HttpServletResponse response) {
        userService.login(signInRequestDto, response);
        return ResponseEntity.ok(new UserMsgResponseDto("로그인 완료",HttpStatus.OK.value()));
    }
}