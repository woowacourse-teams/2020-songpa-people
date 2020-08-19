package com.songpapeople.hashtagmap.docs.instagram;

import com.songpapeople.hashtagmap.docs.ApiDocument;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentRequest;
import static com.songpapeople.hashtagmap.docs.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

public class InstagramApiDocumentation extends ApiDocument {
    protected RestDocumentationResultHandler getDocumentByUpdate() {
        return document("instagram/scheduler/update",
                getDocumentRequest(),
                getDocumentResponse());
    }
}
