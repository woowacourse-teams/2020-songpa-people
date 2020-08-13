package com.songpapeople.hashtagmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTagMapWebApplicationTest {
    @Test
    void main() {
        HashTagMapWebApplication.main(new String[]{"--server.port=0"});
    }
}
