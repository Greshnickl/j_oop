import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FileMonitor {
    private final String folderPath;
    private Map<String, FileSnapshot> snapshots;
    private LocalDateTime lastCommitTime;

    public FileMonitor(String folderPath) {
        this.folderPath = folderPath;
        this.snapshots = new HashMap<>();
        commit();
    }
    public void commit() {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    snapshots.put(file.getName(), new FileSnapshot(file));
                }
            }
        }

        snapshots.keySet().removeIf(fileName -> !new File(folderPath, fileName).exists());

        lastCommitTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        System.out.println("Created Snapshot at: " + lastCommitTime.format(formatter));
    }




    public void printFileInfo(String filename) {
        File file = new File(folderPath, filename);
        if (file.exists() && file.isFile()) {
            System.out.println("File Name: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Size: " + file.length() + " bytes");

            LocalDateTime lastModified = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            System.out.println("Last Modified: " + lastModified.format(formatter));

        } else {
            System.out.println("File does not exist.");
        }
    }

    public void printStatus() {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        Map<String, Boolean> currentFiles = new HashMap<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    currentFiles.put(file.getName(), true);
                    FileSnapshot snapshot = snapshots.get(file.getName());
                    if (snapshot == null) {
                        System.out.println(file.getName() + " - New File");
                    } else if (!snapshot.isUnchanged(file)) {
                        System.out.println(file.getName() + " - Changed");
                    } else {
                        System.out.println(file.getName() + " - No Change");
                    }
                }
            }
        }

        for (String fileName : snapshots.keySet()) {
            if (!currentFiles.containsKey(fileName)) {
                System.out.println(fileName + " - Deleted");
            }
        }
    }
    public void checkForChanges() {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    FileSnapshot currentSnapshot = snapshots.get(file.getName());
                    if (currentSnapshot == null) {
                        System.out.println(file.getName() + " - New File");
                        snapshots.put(file.getName(), new FileSnapshot(file));
                    } else if (!currentSnapshot.isUnchanged(file)) {
                        System.out.println(file.getName() + " - Changed");
                        snapshots.put(file.getName(), new FileSnapshot(file));
                    }
                }
            }

        }
    }
}