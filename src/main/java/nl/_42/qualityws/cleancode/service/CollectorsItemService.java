package nl._42.qualityws.cleancode.service;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import io.beanmapper.BeanMapper;
import nl._42.qualityws.cleancode.csv.AlbumCsvRecord;
import nl._42.qualityws.cleancode.csv.BookCsvRecord;
import nl._42.qualityws.cleancode.csv.CollectorsItemCsvReader;
import nl._42.qualityws.cleancode.csv.MovieCsvRecord;
import nl._42.qualityws.cleancode.model.Album;
import nl._42.qualityws.cleancode.model.Book;
import nl._42.qualityws.cleancode.model.CollectorsItem;
import nl._42.qualityws.cleancode.model.Movie;
import nl._42.qualityws.cleancode.model.validator.CollectorsItemValidator;
import nl._42.qualityws.cleancode.repository.AlbumRepository;
import nl._42.qualityws.cleancode.repository.BookRepository;
import nl._42.qualityws.cleancode.repository.CollectorsItemRepository;
import nl._42.qualityws.cleancode.repository.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CollectorsItemService {

    protected final static Logger LOGGER = LoggerFactory.getLogger(CollectorsItemService.class);

    @Autowired
    private CollectorsItemRepository collectorsItemRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CollectorsItemCsvReader csvReader;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CollectorsItemValidator validator;

    public Album createAlbum(Album album) {
        notNull(album, "Album to create may not be null");
        isTrue(album.isNew(), "Cannot create existing album");
        return collectorsItemRepository.save(album);
    }

    public Book createBook(Book book) {
        notNull(book, "Book to create may not be null");
        isTrue(book.isNew(), "Cannot create existing book");
        return collectorsItemRepository.save(book);
    }

    public Movie createMovie(Movie movie) {
        notNull(movie, "Movie to create may not be null");
        isTrue(movie.isNew(), "Cannot create existing movie");
        return collectorsItemRepository.save(movie);
    }

    public void importBooks(InputStream bookStream) {
        List<BookCsvRecord> bookCsvRecords = csvReader.read(bookStream, BookCsvRecord.class);
        Collection<Book> books = beanMapper.map(bookCsvRecords, Book.class);
        merge(books);
    }

    public void importMovies(InputStream movieStream) {
        List<MovieCsvRecord> movieCsvRecords = csvReader.read(movieStream, MovieCsvRecord.class);
        Collection<Movie> movies = beanMapper.map(movieCsvRecords, Movie.class);
        merge(movies);
    }

    public void importAlbums(InputStream albumStream) {
        List<AlbumCsvRecord> albumCsvRecords = csvReader.read(albumStream, AlbumCsvRecord.class);
        Collection<Album> albums = beanMapper.map(albumCsvRecords, Album.class);
        merge(albums);
    }

    private <T extends CollectorsItem> void merge(Collection<T> items) {
        for (T item : items) {
            if (!validator.validate(item)) {
                continue;
            }
            collectorsItemRepository.save(mergeItem(item));
        }
    }

    private CollectorsItem mergeItem(CollectorsItem item) {
        CollectorsItem foundItem = collectorsItemRepository.findByName(item.getName());
        if (foundItem == null) {
            return item;
        }
        LOGGER.info("Item already exists [{}], merging", item.getName());
        return beanMapper.map(item, foundItem);
    }

    public Page<Movie> listMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Page<Album> listAlbums(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    public Page<Book> listBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

}
