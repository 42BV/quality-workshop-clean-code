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

        final List<ValidationError> errors = new ArrayList<>();

        String header = "";
        String itemName = "";
        String itemPerson = null;

        // +--------------------------+
        // | >>> ALBUM VALIDATION <<< |
        // +--------------------------+
        if (item instanceof Album) {

            Album album = (Album)item;
            header = "album";
            itemName = album.getName();
            itemPerson = album.getArtist();

            // The Album URL must be a Spotify URL
            if (!album.getSpotifyUrl().startsWith("https://open.spotify.com/album/")) {
                errors.add(new ValidationError(StringFormatter.format("illegal Spotify URL %s", album.getSpotifyUrl()).getValue()));
            }

            // Make sure the Artist exists only of permitted characters and starts with a uppercase letter
            String artist = album.getArtist();
            Matcher matcher = pattern.matcher(artist);
            if (    !(matcher.matches() &&
                    Character.isUpperCase(artist.charAt(0)))) {
                errors.add(new ValidationError(StringFormatter.format("illegal Album artist [%s]", artist).getValue()));
            }

            // +-------------------------+
            // | >>> BOOK VALIDATION <<< |
            // +-------------------------+
        } else if (item instanceof Book) {

            Book book = (Book)item;
            header = "book";
            itemName = book.getName();
            itemPerson = book.getAuthor();

            // The Book URL must be a Amazon URL
            if (!book.getAmazonUrl().startsWith("https://www.amazon.com/dp/")) {
                errors.add(new ValidationError(StringFormatter.format("illegal Amazon URL %s", book.getAmazonUrl()).getValue()));
            }

            // Make sure the Author exists only of permitted characters and starts with a uppercase letter
            String author = book.getAuthor();
            Matcher matcher = pattern.matcher(author);
            if (    !(matcher.matches() &&
                    Character.isUpperCase(author.charAt(0)))) {
                errors.add(new ValidationError(StringFormatter.format("illegal Book author [%s]", author).getValue()));
            }

            // +--------------------------+
            // | >>> MOVIE VALIDATION <<< |
            // +--------------------------+
        } else if (item instanceof Movie) {

            Movie movie = (Movie)item;
            header = "movie";
            itemName = movie.getName();
            itemPerson = null;

            // The Movie URL must be an IMDB URL
            if (!movie.getImdbUrl().startsWith("http://www.imdb.com/title/")) {
                errors.add(new ValidationError(StringFormatter.format("illegal IMDB URL %s", movie.getImdbUrl()).getValue()));
            }
        } else {
            throw new RuntimeException("Illegal class: " + item.getClass().getSimpleName());
        }

        // If there are errors, send them to the log
        if (!errors.isEmpty()) {
            if (itemPerson != null) {
                LOGGER.error("Errors for {} [{}] with person [{}]", header, itemName, itemPerson);
            } else {
                LOGGER.error("Errors for {} [{}]", header, itemName);
            }
            errors.forEach(error -> LOGGER.error("- {}", error.getDescription()));
            return false;
        } else {
            return true;
        }

    }

}
