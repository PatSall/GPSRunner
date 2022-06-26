package sports;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ReadActivityThread extends Thread {

    final ActivityList parent;
    //Path file;
    List<Path> file;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    //static int firstFiles;
    SAXParser saxParser = null;
    Activity activity = null;


    public ReadActivityThread(ActivityList parent, List<Path> file) {
        this.parent = parent;
        this.file = file;
    }

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
