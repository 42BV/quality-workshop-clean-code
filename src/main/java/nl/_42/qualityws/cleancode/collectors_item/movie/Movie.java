package nl._42.qualityws.cleancode.collectors_item.movie;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

@Entity
@DiscriminatorValue("movie")
public class Movie extends CollectorsItem {

    private String imdbUrl;

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

}
