package sports;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;

/**
 * Activity ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Susanne Gumplmayr
 */
public class MapActivityObjectHandlerSaxTimestamp extends DefaultHandler {


    private final StringBuilder currentValue = new StringBuilder();
    FileTCX currentFileTCX;

    /**
     * @return
     */
    public FileTCX getFileResult () {
        return currentFileTCX;
    }

    /**
     *
     */
    @Override
    public void startDocument() {

    }

    /**
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     */
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
