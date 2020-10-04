package com.songpapeople.hashtagmap.web.slack;

import com.songpapeople.hashtagmap.web.model.ErrorLog;
import com.songpapeople.hashtagmap.web.property.LogProperties;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;

import java.util.Collections;
import java.util.List;

public class SlackApiWrapper {
    private final SlackApi slackApi;
    private final LogProperties logProperties;

    public SlackApiWrapper(LogProperties logProperties) {
        this.logProperties = logProperties;
        this.slackApi = new SlackApi(logProperties.getSlackWebHookUrl());
    }

    public void call(ErrorLog errorLog) {
        List<SlackField> fields = SlackFieldGroup.collect(errorLog);

        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback(":rotating_light: 에러발생 :rotating_light:");
        slackAttachment.setColor("danger");
        slackAttachment.setFields(fields);

        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.setChannel("#" + logProperties.getSlackChannel());
        slackMessage.setUsername("에러가 터지면 울리는 사이렌");
        slackMessage.setIcon(":rotating_light:");
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));

        slackApi.call(slackMessage);
    }


}
