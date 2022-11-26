package base;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class used to read the data from property file
 * @author TestYantra
 */
public class FileUtility {
    /**
     * This method return the value associated with key in property file and all the key value are defined under folder Test data
     * with file name TestData.properties
     * @param key
     * @return value
     */
    public static String getPropertyValue(String key) 
    {
        try {
            FileInputStream file = new FileInputStream(FilePaths.PROPERTYFILE);
            Properties properties = new Properties();
            properties.load(file);
            return properties.getProperty(key);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return "No Such Key in property file: " + key;
    }
}
