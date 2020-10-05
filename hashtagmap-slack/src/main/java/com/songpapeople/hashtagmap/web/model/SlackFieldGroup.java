package com.songpapeople.hashtagmap.web.model;

import net.gpedro.integrations.slack.SlackField;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SlackFieldGroup {
    MESSAGE("에러 메세지", ErrorLog::getMessage, false),
    URL("요청 URI", ErrorLog::getRequestURI, false),
    TIME("발생 시간", ErrorLog::getTime, true),
    PROFILE("프로파일", ErrorLog::getPhase, true),
    HOST("호스트", ErrorLog::getHostName, false),
    USER_ADDRESS("사용자 IP", ErrorLog::getUserAddress, true),
    USER_AGENT("사용자 환경", ErrorLog::getAgentDetail, true),
    REFERER("REFERER", ErrorLog::getReferer, false),
    REQUEST_HEADER("HTTP HEADER", ErrorLog::getHeaders, false),
    REQUEST_BODY("HTTP BODY", ErrorLog::getRequestBody, false),
    STACK_TRACE("STACK TRACE", ErrorLog::getTrace, false);

    private final String title;
    private final Function<ErrorLog, String> valueFunction;
    private final boolean shorten;

    SlackFieldGroup(final String title, final Function<ErrorLog, String> valueFunction, final boolean shorten) {
        this.title = title;
        this.valueFunction = valueFunction;
        this.shorten = shorten;
    }

    public static List<SlackField> collect(ErrorLog errorLog) {
        return Arrays.stream(values())
                .map(field -> field.makeSlackFieldWrapper(errorLog))
                .collect(Collectors.toList());
    }

    public SlackField makeSlackFieldWrapper(final ErrorLog errorLog) {
        SlackField slackField = new SlackField();
        slackField.setTitle(this.title);
        slackField.setValue(this.valueFunction.apply(errorLog));
        slackField.setShorten(this.shorten);
        return slackField;
    }

    public String getValue(ErrorLog errorLog) {
        return this.valueFunction.apply(errorLog);
    }
}
