package sports;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class FileTCX {
    private String fileName;
    private LocalDateTime startTime;
    private Path file;
    public FileTCX() {

    }

    public FileTCX(String fileName, LocalDateTime startTime) {
        this.fileName = fileName;
        this.startTime = startTime;
    }

    public FileTCX(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }
}
