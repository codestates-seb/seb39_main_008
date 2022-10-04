package it.mainPr.exception;

import lombok.Getter;

public enum ExceptionCode {
    NO_AUTHORIZED(401, "No Authentication"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    DIARY_NOT_FOUND(404, "Post not found"),
    BOOK_NOT_FOUND(404, "Book not found"),

    UNDEFINED(99, "Undefined"),

    TOKEN_EXPIRED(401, "Token Expired"),
    MEMBER_EXISTS(409, "Member exists"),
    ALREADY_FOLLOWED(409, "이미 팔로우된 유저입니다."),
    CAN_NOT_FOLLOW(409, "자신은 팔로우할 수 없습니다."),
    NOT_FOLLOWED(409, "팔로우되지 않은 유저입니다.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}