package nl._42.qualityws.cleancode.collectors_item.service;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.beanmapper.BeanMapper;
import nl._42.qualityws.cleancode.collectors_item.Album;
import nl._42.qualityws.cleancode.collectors_item.Book;
import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;
import nl._42.qualityws.cleancode.collectors_item.Movie;
import nl._42.qualityws.cleancode.collectors_item.csv.AlbumCsvRecord;
import nl._42.qualityws.cleancode.collectors_item.csv.BookCsvRecord;
import nl._42.qualityws.cleancode.collectors_item.csv.CsvReader;
import nl._42.qualityws.cleancode.collectors_item.csv.MovieCsvRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CollectorsItemService {

    @Autowired
    private CollectorsItemRepository collectorsItemRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BeanMapper beanMapper;

    public <T extends CollectorsItem> T create(T item) {
        notNull(item, "Collectors' item to create may not be null");
        isTrue(item.isNew(), "Cannot create existing collectors' item");
        return collectorsItemRepository.save(item);
    }

    public void importBooks(InputStream bookStream) {
        CsvReader<BookCsvRecord> reader = new CsvReader<>(BookCsvRecord.class);
        List<BookCsvRecord> bookRecords = reader.read(bookStream);
        Collection<Book> books = beanMapper.map(bookRecords, Book.class);
        for (Book book : books) {
            Book foundBook = bookRepository.findByName(book.getName());
            if (foundBook != null) {
                book = beanMapper.map(book, foundBook);
            }
            bookRepository.save(book);
        }
    }

    public void importMovies(InputStream movieStream) {
        CsvReader<MovieCsvRecord> reader = new CsvReader<>(MovieCsvRecord.class);
        List<MovieCsvRecord> movieRecords = reader.read(movieStream);
        Collection<Movie> movies = beanMapper.map(movieRecords, Movie.class);
        for (Movie movie : movies) {
            Movie foundMovie = movieRepository.findByName(movie.getName());
            if (foundMovie != null) {
                movie = beanMapper.map(movie, foundMovie);
            }
            movieRepository.save(movie);
        }
    }

    public void importAlbums(InputStream albumStream) {
        CsvReader<AlbumCsvRecord> reader = new CsvReader<>(AlbumCsvRecord.class);
        List<AlbumCsvRecord> albumRecords = reader.read(albumStream);
        Collection<Album> albums = beanMapper.map(albumRecords, Album.class);
        for (Album album : albums) {
            Album foundAlbum = albumRepository.findByName(album.getName());
            if (foundAlbum != null) {
                album = beanMapper.map(album, foundAlbum);
            }
            albumRepository.save(album);
        }
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
