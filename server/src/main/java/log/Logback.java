package log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logback {
    private static final Logger logger = LogManager.getLogger(Logback.class.getName());

    public static Logger getLogger() {
        return logger;
    }
}
