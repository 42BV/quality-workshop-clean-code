package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumMerger extends AbstractCollectorsItemMerger<Album> {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    protected boolean validate(Album album) {
        return album.getSpotifyUrl().startsWith("https://open.spotify.com/album/");
    }

    @Override
    protected void logValidateError(Album album) {
        LOGGER.error("Illegal Spotify URL {} for album [{}]", album.getSpotifyUrl(), album.getName());
    }

    @Override
    protected Album lookupItem(String name) {
        return albumRepository.findByName(name);
    }

    @Override
    protected Album save(Album album) {
        return albumRepository.save(album);
    }

}
