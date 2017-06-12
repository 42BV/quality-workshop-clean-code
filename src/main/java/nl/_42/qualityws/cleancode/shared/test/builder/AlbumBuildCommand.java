package nl._42.qualityws.cleancode.shared.test.builder;

import nl._42.beanie.EditableBeanBuildCommand;
import nl._42.qualityws.cleancode.model.Album;
import nl._42.qualityws.cleancode.model.Collector;

public interface AlbumBuildCommand extends EditableBeanBuildCommand<Album> {
    
    AlbumBuildCommand withName(String name);
    AlbumBuildCommand withCollector(Collector collector);
    AlbumBuildCommand withSpotifyUrl(String spotifyUrl);
    AlbumBuildCommand withArtist(String artist);
}
