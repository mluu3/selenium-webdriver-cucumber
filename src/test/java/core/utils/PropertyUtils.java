package core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    private Properties properties;
    private String propertyFile = System.getProperty("user.dir") + "/config.properties";

    public PropertyUtils() {
        try (InputStream input = new FileInputStream(propertyFile)) {
            properties = new Properties();

            // load a properties file
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PropertyUtils setFile(String propertyFile) {
        this.propertyFile = propertyFile;
        return this;
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }

}