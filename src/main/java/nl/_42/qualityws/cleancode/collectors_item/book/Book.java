package nl._42.qualityws.cleancode.collectors_item.book;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

@Entity
@DiscriminatorValue("book")
public class Book extends CollectorsItem {

    private String amazonUrl;

    public String getAmazonUrl() {
        return amazonUrl;
    }

    public void setAmazonUrl(String amazonUrl) {
        this.amazonUrl = amazonUrl;
    }
}
