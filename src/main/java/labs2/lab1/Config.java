package labs2.lab1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static Properties properties;
    private static Config instance;

    private Config() {
        properties = new Properties();
        try (InputStreamReader stream = new InputStreamReader(Files.newInputStream(Paths.get("./src/main/resources/config.properties")))) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод можно делать и не synchronized, т.к. synchronized дает защиту для многопоточности, которой у нас нет
    public synchronized static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
