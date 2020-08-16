package com.songpapeople.hashtagmap.exception;

import lombok.Getter;

@Getter
public enum AdminExceptionStatus {
    NOT_FOUND_SCHEDULER("ADMIN_1000", "스케쥴러가 존재하지 않습니다."),
    NOT_FOUND_TAG_LEVEL("ADMIN_1001", "테그레벨이 존재하지 않습니다."),
    INVALID_TAG_LEVEL_UPDATE("ADMIN_1002", "테그레벨 정보를 갱신할 수 없습니다."),
    NOT_FOUNT_NICK_NAME("ADMIN_2000","존재하지 않는 아이디입니다."),
    WRONG_PASSWORD("ADMIN_2001","비밀번호가 일치하지 않습니다.");

    private final String code;
    private final String message;

    AdminExceptionStatus(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
