package nl._42.qualityws.cleancode.collectors_item;

import static io.beanmapper.spring.PageableMapper.map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.beanmapper.BeanMapper;
import io.beanmapper.spring.web.MergedForm;
import nl._42.qualityws.cleancode.collectors_item.album.Album;
import nl._42.qualityws.cleancode.collectors_item.album.AlbumForm;
import nl._42.qualityws.cleancode.collectors_item.album.AlbumResult;
import nl._42.qualityws.cleancode.collectors_item.book.Book;
import nl._42.qualityws.cleancode.collectors_item.book.BookForm;
import nl._42.qualityws.cleancode.collectors_item.book.BookResult;
import nl._42.qualityws.cleancode.collectors_item.movie.Movie;
import nl._42.qualityws.cleancode.collectors_item.movie.MovieForm;
import nl._42.qualityws.cleancode.collectors_item.movie.MovieResult;

@RestController
@RequestMapping("items")
public class CollectorsItemController {

    @Autowired
    private CollectorsItemService itemService;
    @Autowired
    private BeanMapper beanMapper;
    
    @RequestMapping("/movies")
    public Page<MovieResult> listMovies(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return map(itemService.listMovies(pageable), MovieResult.class, beanMapper);
    }
    
    @RequestMapping("/albums")
    public Page<AlbumResult> listAlbums(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return map(itemService.listAlbums(pageable), AlbumResult.class, beanMapper);
    }
    
    @RequestMapping("/books")
    public Page<BookResult> listBooks(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return map(itemService.listBooks(pageable), BookResult.class, beanMapper);
    }
    
    @RequestMapping(path = "/movies", method = RequestMethod.POST)
    public MovieResult createMovie(@Valid @MergedForm(value = MovieForm.class) Movie movie) {
        return beanMapper.map(itemService.create(movie), MovieResult.class);
    }

    @RequestMapping(path = "/albums", method = RequestMethod.POST)
    public AlbumResult createAlbum(@Valid @MergedForm(value = AlbumForm.class) Album album) {
        return beanMapper.map(itemService.create(album), AlbumResult.class);
    }

    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public BookResult createBook(@Valid @MergedForm(value = BookForm.class) Book book) {
        return beanMapper.map(itemService.create(book), BookResult.class);
    }

}
