package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Album;

import org.springframework.stereotype.Component;

@Component
class AlbumValidator extends AbstractCollectorsItemValidator<Album> {

    @Override
    protected boolean validateItem(Album album) {
        return album.getSpotifyUrl().startsWith("https://open.spotify.com/album/");
    }

    @Override
    protected void logError(Album album) {
        LOGGER.error("Illegal Spotify URL {} for album [{}]", album.getSpotifyUrl(), album.getName());
    }

}
