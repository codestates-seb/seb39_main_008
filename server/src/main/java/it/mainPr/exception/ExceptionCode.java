package it.mainPr.exception;

import lombok.Getter;

public enum ExceptionCode {
    NO_AUTHORIZED(401, "No Authentication"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    DIARY_NOT_FOUND(404, "Post not found"),
    BOOK_NOT_FOUND(404, "Book not found"),
    UNDEFINED(99, "Undefined"),
    HEART_EXIST(409,"Heart exists"),
    HEART_NOT_FOUND(404,"Heart not found"),

    MEMBER_EXISTS(409, "Member exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}