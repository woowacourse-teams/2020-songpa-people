package com.songpapeople.hashtagmap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MockDataFactory {

    public static String createBody() {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(new File("src/test/resources/crawling-mock-data.txt"))) {
            while (true) {
                int fileData = fileReader.read();
                if (fileData == -1) {
                    break;
                }
                builder.append((char) fileData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
