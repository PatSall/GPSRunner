package Test;

import org.junit.jupiter.api.Test;
import sports.FileTCX;
import sports.MapActivityObjectHandlerSaxTimestamp;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileTCXTest {

    @Test
    void testSetter_Getter_FileName() {
        String testfile = "test.tcx";
        FileTCX tcxFile = new FileTCX();
        String file;

        tcxFile.setFileName(testfile);
        assertEquals(testfile, tcxFile.getFileName());
    }
}