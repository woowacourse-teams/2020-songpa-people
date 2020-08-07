package com.songpapeople.hashtagmap.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Todo 순서 컨벤션 확인
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomResponse<T> {
    private T data;
    private String code;
    private String message;

    public CustomResponse(final T data, final String code, final String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <Void> CustomResponse<Void> empty() {
        return new CustomResponse<>(null, null, null);
    }

    public static <Void> CustomResponse<Void> error(final String code) {
        return new CustomResponse<>(null, code, null);
    }

    public static <Void> CustomResponse<Void> error(final String code, final String message) {
        return new CustomResponse<>(null, code, message);
    }

    public static <T> CustomResponse<T> of(final T data) {
        return new CustomResponse<>(data, null, null);
    }

    public static <T> CustomResponse<T> of(final T data, final String code) {
        return new CustomResponse<>(data, code, null);
    }

    public static <T> CustomResponse<T> of(final T data, final String code, final String message) {
        return new CustomResponse<>(data, code, message);
    }
}
