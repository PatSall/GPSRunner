package sports;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;

public class MapActivityObjectHandlerSaxTimestamp extends DefaultHandler {


    private final StringBuilder currentValue = new StringBuilder();
    FileTCX currentFileTCX;

    public FileTCX getFileResult () {
        return currentFileTCX;
    }

    @Override
    public void startDocument() {

    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);


        if (qName.equalsIgnoreCase("Lap")) {
            currentFileTCX = new FileTCX();
            currentFileTCX.setStartTime(LocalDateTime.parse(attributes.getValue("StartTime").substring(0, 19)));
            return;

        }
    }



}
