package com.songpapeople.hashtagmap.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ErrorLog {

    private Long id;

    private String phase;

    private String system;

    private String loggerName;

    private String serverName;

    private String hostName;

    private String path;

    private String message;

    private String trace;

    private LocalDateTime errorDatetime = LocalDateTime.now();

    private String alertYn = "N";

    private String headerMap;

    private String parameterMap;

    private String userInfo;

    private String agentDetail;
}