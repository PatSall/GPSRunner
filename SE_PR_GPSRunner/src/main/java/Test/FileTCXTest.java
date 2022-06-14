package Test;

import sports.FileTCX;
import sports.MapActivityObjectHandlerSaxTimestamp;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileTCXTest {
    public String testfile = "test.tcx";
    FileTCX tcxFile;
    private Path file;

    {
        tcxFile.setFileName(file.getFileName().toString());
    }
}