package common.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * User: zinchenko
 * Date: 9/19/13
 */
public class Main {
    public static void main(String[] args) throws ConfigurationException, InterruptedException {
        PropertiesConfiguration conf = new PropertiesConfiguration("my.properties");
        String value = conf.getString("key");

        Thread.sleep(100000000L);

        System.out.println();
    }
}
