package com.songpapeople.hashtagmap.crawler;

import com.google.gson.JsonElement;

class JsonExplorer {
    public static final String PARENT_KEY = "node";

    private JsonExplorer() {
    }

    public static String findByKey(JsonElement edge, String key) {
        return edge.getAsJsonObject()
                .get(PARENT_KEY).getAsJsonObject()
                .get(key).getAsString();
    }
}
