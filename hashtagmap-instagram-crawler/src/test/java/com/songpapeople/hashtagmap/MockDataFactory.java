package com.songpapeople.hashtagmap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MockDataFactory {

    public static String createBody() throws IOException {
        StringBuilder builder = new StringBuilder();
        File file = new File("src/test/resources/crawling-mock-data.txt");
        try (FileReader fileReader = new FileReader(file)) {
            int fileData;
            while ((fileData = fileReader.read()) != -1) {
                builder.append((char) fileData);
            }
        }
        return builder.toString();
    }
}
