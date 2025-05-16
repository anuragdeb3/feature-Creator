package utility;

import java.io.InputStream;
import java.util.Properties;

    public class configLoader {
        private final Properties props = new Properties();

        public configLoader() throws Exception {
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                props.load(input);
            }
        }

        public String get(String key) {
            return props.getProperty(key);
        }
    }


