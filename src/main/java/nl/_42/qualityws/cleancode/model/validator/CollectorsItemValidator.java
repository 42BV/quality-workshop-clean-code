package nl._42.qualityws.cleancode.model.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl._42.qualityws.cleancode.model.Album;
import nl._42.qualityws.cleancode.model.Book;
import nl._42.qualityws.cleancode.model.CollectorsItem;
import nl._42.qualityws.cleancode.model.Movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.javafx.binding.StringFormatter;

@Component
public class CollectorsItemValidator {

    protected final static Logger LOGGER = LoggerFactory.getLogger(CollectorsItemValidator.class);

    private static Pattern pattern = Pattern.compile("[a-zA-Z -\\.]+");

    public boolean validate(CollectorsItem item) {
        if (item instanceof Album) {
            return validateAlbum((Album)item);
        } else if (item instanceof Book) {
            return validateBook((Book)item);
        } else if (item instanceof Movie) {
            return validateMovie((Movie)item);
        } else {
            throw new RuntimeException("Illegal class: " + item.getClass().getSimpleName());
        }
    }

    private boolean validateAlbum(Album album) {
        final List<ValidationError> errors = new ArrayList<>();

        verifyUrl(
                errors,
                album.getSpotifyUrl(),
                "https://open.spotify.com/album/",
                "illegal Spotify URL %s");

        verifyPersonName(
                errors,
                album.getArtist(),
                "illegal Album artist [%s]");

        return checkForErrors(
                errors,
                new CollectorsItemLogHeader("album", album.getName(), album.getArtist()));
    }

    private boolean validateBook(Book book) {
        final List<ValidationError> errors = new ArrayList<>();

        verifyUrl(
                errors,
                book.getAmazonUrl(),
                "https://www.amazon.com/dp/",
                "illegal Amazon URL %s");

        verifyPersonName(
                errors,
                book.getAuthor(),
                "illegal Book author [%s]");

        return checkForErrors(
                errors,
                new CollectorsItemLogHeader("book", book.getName(), book.getAuthor()));
    }

    private boolean validateMovie(Movie movie) {
        final List<ValidationError> errors = new ArrayList<>();

        verifyUrl(
                errors,
                movie.getImdbUrl(),
                "http://www.imdb.com/title/",
                "illegal IMDB URL %s");

        return checkForErrors(
                errors,
                new CollectorsItemLogHeader("movie", movie.getName(), null));
    }

    private void verifyUrl(List<ValidationError> errors, String url, String startsWith, String errorsMsg) {
        if (!url.startsWith(startsWith)) {
            errors.add(new ValidationError(StringFormatter.format(errorsMsg, url).getValue()));
        }
    }

    private void verifyPersonName(List<ValidationError> errors, String person, String errorMsg) {
        Matcher matcher = pattern.matcher(person);
        if (    !(matcher.matches() &&
                Character.isUpperCase(person.charAt(0)))) {
            errors.add(new ValidationError(StringFormatter.format(errorMsg, person).getValue()));
        }
    }

    private boolean checkForErrors(List<ValidationError> errors, CollectorsItemLogHeader header) {
        if (!errors.isEmpty()) {
            header.logError();
            errors.forEach(error -> LOGGER.error("- {}", error.getDescription()));
            return false;
        } else {
            return true;
        }
    }

}
