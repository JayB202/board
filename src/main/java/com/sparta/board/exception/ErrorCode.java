package com.sparta.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    BOARD_NOT_FOUND(BAD_REQUEST, "게시글 확인 안됨"),
    COMMENT_NOT_FOUND(BAD_REQUEST, "댓글 확인 안됨"),
    USER_NOT_FOUND(BAD_REQUEST, "사용자 확인 안됨"),

    CANNOT_UPDATE(BAD_REQUEST, "수정 권한 없음"),
    CANNOT_DELETE(BAD_REQUEST, "삭제 권한 없음"),

    WRONG_TOKEN(BAD_REQUEST, "토큰이 잘못됨 "),

    NOT_MATCH_ADMIN_TOKEN(BAD_REQUEST, "관리자 암호가 다름"),

    NONEXISTENT_USER(BAD_REQUEST, "회원을 찾을 수 없음"),
    EXIST_USERNAME(BAD_REQUEST, "중복된 이름");

    private final HttpStatus httpStatus;
    private final String detail;


}