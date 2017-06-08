package nl._42.qualityws.cleancode.collectors_item.csv;

import org.csveed.annotations.CsvCell;
import org.csveed.annotations.CsvFile;
import org.csveed.bean.ColumnNameMapper;

@CsvFile(separator = ',', mappingStrategy = ColumnNameMapper.class)
public class AlbumCsvRecord {

    @CsvCell
    private String owner;
    @CsvCell
    private String web;
    @CsvCell
    private String album;
    @CsvCell
    private String artist;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

/*
owner,web,album,artist
Steven de Wolf,https://open.spotify.com/album/7kUiJdXqLkMTkpY0PmXUv5,Something To Remember,Madonna

 */