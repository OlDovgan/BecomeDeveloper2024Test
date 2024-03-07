package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor();
        Properties properties = new Properties();
        try (InputStream input = Main.class.getResourceAsStream("/app.properties")) {
            if (input == null) {
                logger.log(Level.SEVERE, "Failed to load properties file");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties file", e);
            return;
        }

        String filename = properties.getProperty("filename");
        List<String> list = new FileReader(filename).readList();
        ListOfInteger listOfInteger = new ListOfInteger(list);
        Method[] methods = ListOfInteger.class.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("find")) {
                executor.executeMethod(listOfInteger, method.getName());
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Full  time : " + (endTime - startTime) / 1_000_000 + " milliseconds");
        System.out.println("Press Enter to exit");
        scanner.nextLine();
    }
}