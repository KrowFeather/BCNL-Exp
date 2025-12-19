package com.krowfeather.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();
    static {
        try{
            InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getConfig(String key){
        return properties.getProperty(key);
    }
}
