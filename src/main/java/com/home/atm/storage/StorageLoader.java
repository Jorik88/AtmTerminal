package com.home.atm.storage;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StorageLoader {

    private static final Logger LOGGER = Logger.getLogger(StorageLoader.class);
    public static final String ACCOUNT_DIRECTORY = System.getenv("ACCOUNT_REPOSITORY");

    public Storage loadStorage(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        Map<String, Map<String, Integer>> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            Map<String, Integer> map1 = new HashMap<>();
            String[] result = scanner.nextLine().split(" ");
            for (int i = 1; i < result.length; i = i + 2) {
                map1.put(result[i], Integer.valueOf(result[i + 1]));
            }
            map.put(result[0], map1);
        }
        LOGGER.info("Loaded from " + file.getAbsolutePath() + ":" + map);
        return new Storage(map);
    }
}
