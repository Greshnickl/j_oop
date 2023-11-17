import java.io.File;

public class FileSnapshot {
    private long lastModifiedTime;
    private long fileSize;

    public FileSnapshot(File file) {
        this.lastModifiedTime = file.lastModified();
        this.fileSize = file.length();
    }

    public boolean isUnchanged(File file) {
        return file.lastModified() == lastModifiedTime && file.length() == fileSize;
    }
}
