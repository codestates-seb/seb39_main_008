package it.mainPr.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    UNAUTHORIZATIONED(401, "Access Denied"),
    MEMBER_EXISTS(409, "Member exists"),
    NoSuchElementException(500, "Server error");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
