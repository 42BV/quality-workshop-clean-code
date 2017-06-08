package nl._42.qualityws.cleancode.collectors_item.album;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

@Entity
@DiscriminatorValue("album")
public class Album extends CollectorsItem {

    private String spotifyUrl;

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }
}
