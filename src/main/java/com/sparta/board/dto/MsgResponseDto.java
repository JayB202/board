package com.sparta.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MsgResponseDto {
    String msg;
    int statusCode;
}