package sports;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


/**
 * generate thread for the read-in performance
 * @author Susanne Gumplmayr
 */
public class ReadActivityThread extends Thread {

    final ActivityList parent;
    List<Path> file;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = null;
    Activity activity = null;


    public ReadActivityThread(ActivityList parent, List<Path> file) {
        this.parent = parent;
        this.file = file;
    }

    /**
     * iterate the list of file and
     * generate a new object of MapActivityObjectHandlerSax
     */
    public void run() {
        for (Path f : file) {
            try {
                saxParser = factory.newSAXParser();
                MapActivityObjectHandlerSax handlerSax = new MapActivityObjectHandlerSax();
                saxParser.parse(f.toString(), handlerSax);
                activity = handlerSax.getActivityResult();

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
            synchronized (parent) {
                parent.addActivity(activity);
            }

        }

    }
}
