package com.songpapeople.hashtagmap;

import org.junit.jupiter.api.Test;

class HashTagMapAdminApplicationTest {

    @Test
    void main() {
        HashTagMapAdminApplication.main(new String[]{"--server.port=0"});
    }
}