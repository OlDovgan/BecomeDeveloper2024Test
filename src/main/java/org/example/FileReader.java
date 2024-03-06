package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    String name;

    public FileReader(String name) {
        this.name = name;
    }

    public List<String> readList() throws FileNotFoundException, URISyntaxException {
        long startTime = System.nanoTime();
        String jarPath = FileReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String jarDirectory = new File(jarPath).getParent();
        String filePath = jarDirectory + File.separator + this.name;

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Reading  time from file: " + (endTime - startTime) / 1_000_000 + " milliseconds");
        return list;
    }

}
