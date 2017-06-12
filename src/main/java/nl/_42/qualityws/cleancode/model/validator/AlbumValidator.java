package nl._42.qualityws.cleancode.model.validator;

import java.util.ArrayList;
import java.util.List;

import nl._42.qualityws.cleancode.model.Album;

import org.springframework.stereotype.Component;

import com.sun.javafx.binding.StringFormatter;

@Component
public class AlbumValidator extends AbstractPersonNameValidator<Album> {

    @Override
    protected List<ValidationError> validateItem(Album album) {
        final List<ValidationError> errors = new ArrayList<>();
        if (!album.getSpotifyUrl().startsWith("https://open.spotify.com/album/")) {
            errors.add(new ValidationError(StringFormatter.format("illegal Spotify URL %s", album.getSpotifyUrl()).getValue()));
        }
        if (!verifyName(album.getArtist())) {
            errors.add(new ValidationError(StringFormatter.format("illegal Album artist [%s]", album.getArtist()).getValue()));
        }
        return errors;
    }

    @Override
    protected void logErrorHeader(Album album) {
        LOGGER.error("Errors for album [{}] with artist [{}]", album.getName(), album.getArtist());
    }

}
