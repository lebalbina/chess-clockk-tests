package com.balbina.clockktests.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {
    private static final Properties resolvedProperties;

    static {
        resolvedProperties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            resolvedProperties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return resolvedProperties.getProperty(key);
    }
}

