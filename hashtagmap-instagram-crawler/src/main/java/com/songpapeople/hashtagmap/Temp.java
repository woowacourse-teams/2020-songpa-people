package com.songpapeople.hashtagmap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Temp {

    public static List<String> getList() {
        try (FileReader fileReader = new FileReader(new File("C:/Users/ebseu/Desktop/식당리스트.txt"))) {
            StringBuilder builder = new StringBuilder();
            while (true) {
                int fileData = fileReader.read();
                if (fileData == -1) {
                    break;
                }
                builder.append((char)fileData);
            }
            String unprocessedData = builder.toString();
            return Arrays.asList(unprocessedData.split(System.lineSeparator()));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}
