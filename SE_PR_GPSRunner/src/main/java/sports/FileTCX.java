package sports;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Activity ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Patrick Sallaberger & Susanne Gumplmayr
 */
public class FileTCX {
    private String fileName;
    private LocalDateTime startTime;
    private Path file;
    public FileTCX() {

    }

    /**
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return
     */
    public Path getFile() {
        return file;
    }

    /**
     * @param file
     */
    public void setFile(Path file) {
        this.file = file;
    }
}
