package nl._42.qualityws.cleancode.collectors_item;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import nl._42.qualityws.cleancode.collectors_item.album.Album;
import nl._42.qualityws.cleancode.collectors_item.album.AlbumRepository;
import nl._42.qualityws.cleancode.collectors_item.book.Book;
import nl._42.qualityws.cleancode.collectors_item.book.BookRepository;
import nl._42.qualityws.cleancode.collectors_item.movie.Movie;
import nl._42.qualityws.cleancode.collectors_item.movie.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class CollectorsItemService {

    @Autowired
    private CollectorsItemRepository collectorsItemRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BookRepository bookRepository;

    <T extends CollectorsItem> T create(T item) {
        notNull(item, "Collectors' item to create may not be null");
        isTrue(item.isNew(), "Cannot create existing collectors' item");
        return collectorsItemRepository.save(item);
    }
    
    Page<Movie> listMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    Page<Album> listAlbums(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    Page<Book> listBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

}
