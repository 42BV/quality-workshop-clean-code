package nl._42.qualityws.cleancode.collectors_item.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl._42.qualityws.cleancode.collectors_item.Album;
import nl._42.qualityws.cleancode.collectors_item.Book;
import nl._42.qualityws.cleancode.collectors_item.Movie;

@Component
public class CollectorsItemValidatorFacade {

    @Autowired
    private CollectorsItemValidator<Book> bookValidator;
    @Autowired
    private CollectorsItemValidator<Album> albumValidator;
    @Autowired
    private CollectorsItemValidator<Movie> movieValidator;
    
    public boolean validateBook(Book book) {
        return bookValidator.validate(book);
    }
    
    public boolean validateAlbum(Album album) {
        return albumValidator.validate(album);
    }
    
    public boolean validateMovie(Movie movie) {
        return movieValidator.validate(movie);
    }
    
}
