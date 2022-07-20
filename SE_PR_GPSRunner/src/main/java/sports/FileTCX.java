package sports;

import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * FileTCX main purpose for sorting the files
 * @author Patrick Sallaberger & Susanne Gumplmayr
 */
public class FileTCX {
    private String fileName;
    private LocalDateTime startTime;
    private Path file;
    public FileTCX() {

    }

    /**
     * @return file name in String format
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName in String format
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return start time in LocalDateTime format
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime in LocalDateTime format
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return file path in Path format
     */
    public Path getFile() {
        return file;
    }

    /**
     * @param file in Path format
     */
    public void setFile(Path file) {
        this.file = file;
    }
}
