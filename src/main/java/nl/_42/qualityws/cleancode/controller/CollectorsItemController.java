package nl._42.qualityws.cleancode.controller;

import java.io.IOException;

import javax.validation.Valid;

import nl._42.qualityws.cleancode.model.Album;
import nl._42.qualityws.cleancode.model.Book;
import nl._42.qualityws.cleancode.model.Movie;
import nl._42.qualityws.cleancode.service.CollectorsItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("items")
public class CollectorsItemController {

    @Autowired
    private CollectorsItemService itemService;

    @GetMapping("/movies")
    public Page<Movie> listMovies(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return itemService.listMovies(pageable);
    }
    
    @GetMapping("/albums")
    public Page<Album> listAlbums(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return itemService.listAlbums(pageable);
    }
    
    @GetMapping("/books")
    public Page<Book> listBooks(@SortDefault({"collector.name", "name"}) Pageable pageable) {
        return itemService.listBooks(pageable);
    }
    
    @PostMapping(path = "/movies")
    public Movie createMovie(@Valid Movie movie) {
        return itemService.createMovie(movie);
    }

    @PostMapping(path = "/albums")
    public Album createAlbum(@Valid Album album) {
        return itemService.createAlbum(album);
    }

    @PostMapping(path = "/books")
    public Book createBook(@Valid Book book) {
        return itemService.createBook(book);
    }

    @PostMapping("/movies/upload")
    public void uploadMovies(@RequestPart MultipartFile csv) throws IOException {
        itemService.importMovies(csv.getInputStream());
    }
    
    @PostMapping("/albums/upload")
    public void uploadAlbums(@RequestPart MultipartFile csv) throws IOException {
        itemService.importAlbums(csv.getInputStream());
    }
    
    @PostMapping("/books/upload")
    public void uploadBooks(@RequestPart MultipartFile csv) throws IOException {
        itemService.importBooks(csv.getInputStream());
    }
}
