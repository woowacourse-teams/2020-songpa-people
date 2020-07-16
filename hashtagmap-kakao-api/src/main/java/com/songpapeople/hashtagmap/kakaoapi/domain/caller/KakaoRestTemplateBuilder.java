package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.exception.KakaoExceptionHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class KakaoRestTemplateBuilder {
    private static final String BASE_URL = "https://dapi.kakao.com";

    public static RestTemplateBuilder get(KakaoProperties kakaoProperties) {
        return new RestTemplateBuilder()
                .rootUri(BASE_URL)
                .errorHandler(new KakaoExceptionHandler())
                .additionalInterceptors(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                        ClientHttpRequestExecution execution) throws IOException {
                        request.getHeaders().set("Authorization", "KakaoAK " + kakaoProperties.getKey());
                        return execution.execute(request, body);
                    }
                });
    }
}
