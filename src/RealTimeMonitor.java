import java.util.logging.Logger;
import java.util.logging.Level;

public class RealTimeMonitor implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(RealTimeMonitor.class.getName());
    private FileMonitor fileMonitor;
    private boolean running;

    public  RealTimeMonitor(FileMonitor fileMonitor) {
        this.fileMonitor = fileMonitor;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                fileMonitor.checkForChanges();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.log(Level.WARNING, "Real-time monitoring interrupted.", e);
            }
        }
    }

    public void stop() {
        running = false;
    }
}
