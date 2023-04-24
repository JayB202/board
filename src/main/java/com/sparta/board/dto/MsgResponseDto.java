package com.sparta.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgResponseDto {
    private String msg;
    public void setMsg(String msg) {
        this.msg = msg;
    }
}