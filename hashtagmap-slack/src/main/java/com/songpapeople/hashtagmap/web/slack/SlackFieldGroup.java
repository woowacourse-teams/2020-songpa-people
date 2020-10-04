package com.songpapeople.hashtagmap.web.slack;

import com.songpapeople.hashtagmap.web.model.ErrorLog;
import net.gpedro.integrations.slack.SlackField;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SlackFieldGroup {
    MESSAGE("에러 메세지", ErrorLog::getMessage, false),
    URL("요청 URI", ErrorLog::getRequestURI, false),
    TIME("발생 시간", ErrorLog::getTime, false),
    PROFILE("프로파일", ErrorLog::getPhase, false),
    HOST("호스트", ErrorLog::getHostName, false),
    USER_INFO("사용자 정보", ErrorLog::getUserInfo, false),
    USER_AGENT("사용자 환경", ErrorLog::getAgentDetail, false),
    REQUEST_HEADER("HTTP HEADER", ErrorLog::getHeaders, true),
    REQUEST_BODY("HTTP BODY", ErrorLog::getRequestBody, true),
    STACK_TRACE("STACK TRACE", ErrorLog::getTrace, true);

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
                .map(field -> makeSlackFieldWrapper(errorLog, field))
                .collect(Collectors.toList());
    }

    private static SlackField makeSlackFieldWrapper(final ErrorLog errorLog, final SlackFieldGroup field) {
        SlackField slackField = new SlackField();
        slackField.setTitle(field.title);
        slackField.setValue(field.valueFunction.apply(errorLog));
        slackField.setShorten(field.shorten);
        return slackField;
    }
}
