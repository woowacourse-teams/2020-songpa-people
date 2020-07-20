package com.songpapeople.hashtagmap.kakaoapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class KakaoExceptionHandler implements ResponseErrorHandler {
    private static final Map<Integer, String> errorMessages = new HashMap<>();

    static {
        String BAD_REQUEST_MESSAGE = "주로 API에 필요한 필수 파라미터와 관련하여 서버가 클라이언트 오류를 감지해 요청을 처리하지 못한 상태입니다.";
        String UNAUTHORIZED_MESSAGE = "해당 리소스에 유효한 인증 자격 증명이 없어 요청에 실패한 상태입니다.";
        String FORNIDDEN_MESSAGE = "서버에 요청이 전달되었지만, 권한 때문에 거절된 상태입니다.";
        String INTERNAL_SERVER_ERROR_MESSAGE = "서버 에러를 총칭하는 에러 코드로, 요청을 처리하는 과정에서 서버가 예상하지 못한 상황에 놓인 상태입니다.";
        String BAD_GATEWAY_MESSAGE = "서로 다른 프로토콜을 연결해주는 게이트웨이가 잘못된 프로토콜을 연결하거나 연결된 프로토콜에 문제가 있어 통신이 제대로 되지 않은 상태입니다.";
        String SERVICE_UNAVAILABLE_MESSAGE = "서버가 요청을 처리할 준비가 되지 않은 상태입니다.";

        errorMessages.put(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_MESSAGE);
        errorMessages.put(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED_MESSAGE);
        errorMessages.put(HttpStatus.FORBIDDEN.value(), FORNIDDEN_MESSAGE);
        errorMessages.put(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR_MESSAGE);
        errorMessages.put(HttpStatus.BAD_GATEWAY.value(), BAD_GATEWAY_MESSAGE);
        errorMessages.put(HttpStatus.SERVICE_UNAVAILABLE.value(), SERVICE_UNAVAILABLE_MESSAGE);
    }

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        int statusCode = httpResponse.getRawStatusCode();
        throw new KakaoException(statusCode, errorMessages.get(statusCode));
    }
}
