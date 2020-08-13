package com.songpapeople.hashtagmap.docs.instagram;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

public class InstagramApiDocumentation {
    public static RestDocumentationResultHandler getDocumentByUpdate() {
        return document("instagram/scheduler/update",
                getDocumentRequest(),
                getDocumentResponse());
    }
}
