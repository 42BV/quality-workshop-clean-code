package nl._42.qualityws.cleancode.model.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectorsItemLogHeader {

    protected final static Logger LOGGER = LoggerFactory.getLogger(CollectorsItemLogHeader.class);

    private final String header;

    private final String itemName;

    private final String itemPerson;

    public CollectorsItemLogHeader(String header, String itemName, String itemPerson) {
        this.header = header;
        this.itemName = itemName;
        this.itemPerson = itemPerson;
    }

    public String getHeader() {
        return header;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPerson() {
        return itemPerson;
    }

    public void logError() {
        if (itemPerson != null) {
            LOGGER.error("Errors for {} [{}] with person [{}]", header, itemName, itemPerson);
        } else {
            LOGGER.error("Errors for {} [{}]", header, itemName);
        }
    }

}
