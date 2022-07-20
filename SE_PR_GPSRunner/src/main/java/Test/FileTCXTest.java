package Test;

import org.junit.jupiter.api.Test;
import sports.FileTCX;


import static org.junit.jupiter.api.Assertions.*;
/**
 * FileTCXTest test-class
 * @author Stefan Gruber
 */
class FileTCXTest {
    /**
     * tests the function: Setter and Getter: file name
     */
    @Test
    void testSetter_Getter_FileName() {
        String testfile = "test.tcx";
        FileTCX tcxFile = new FileTCX();


        tcxFile.setFileName(testfile);
        assertEquals(testfile, tcxFile.getFileName());
    }
}