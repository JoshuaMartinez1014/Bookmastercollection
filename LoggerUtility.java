package rasmussen.bookmasters;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class LoggerUtility {
    private static Logger logger = Logger.getLogger(LoggerUtility.class.getName());

    static {
        try {
            // Create a FileHandler
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Add the handler to the logger
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // Set the logger to log all levels

            // Disable parent handlers (which will remove console logging)
            logger.setUseParentHandlers(false);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occur in FileHandler setup.", e);
        }
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}