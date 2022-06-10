package sports;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ReadActivityThread extends Thread {

    final ActivityList parent;
    Path file;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = null;
    Activity activity = null;


    public ReadActivityThread(ActivityList parent, Path file) {
        this.parent = parent;
        this.file = file;
    }

    public void run() {


        try {
            saxParser = factory.newSAXParser();
            MapActivityObjectHandlerSax handlerSax = new MapActivityObjectHandlerSax();
            saxParser.parse(file.toString(), handlerSax);
            activity = handlerSax.getActivityResult();
        }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        synchronized (parent) {
            parent.addActivity(activity);
        }
    }
}
