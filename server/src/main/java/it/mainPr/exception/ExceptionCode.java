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
    ACCESS_DENIED_MEMBER(401,"Access Denied"),
    PHOTO_NOT_FOUND(404, "photo not found"),
    FILE_UPLOAD_FAILED(417, "file upload failed"),
    FILE_SIZE_EXCEED(431,"file size exceed"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    COMMENT_EXISTS(409, "Comment exists"),
    SORT_NOT_FOUND(404, "Sort not found"),


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