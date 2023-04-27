package com.sparta.board.dto.userDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequestDto {
    private String username;
    private String password;
}