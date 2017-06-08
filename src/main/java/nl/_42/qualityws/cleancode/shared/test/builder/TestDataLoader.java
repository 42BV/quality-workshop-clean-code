package nl._42.qualityws.cleancode.shared.test.builder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import nl._42.qualityws.cleancode.collectors_item.album.AlbumCsvRecord;
import nl._42.qualityws.cleancode.collectors_item.book.BookCsvRecord;
import nl._42.qualityws.cleancode.collectors_item.movie.MovieCsvRecord;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.api.Header;
import org.csveed.api.Row;
import org.csveed.bean.conversion.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class TestDataLoader implements ResourceLoaderAware {

    private Resource amazonBooksCsv;
    private Resource imdbMoviesCsv;
    private Resource spotifyAlbumsCsv;

    @PostConstruct
    public void load() throws IOException {

//        try(Reader reader = new InputStreamReader(imdbMoviesCsv.getInputStream())) {
//            CsvClient<MovieCsvRecord> client = new CsvClientImpl<>(reader, MovieCsvRecord.class);
//            List<MovieCsvRecord> movies = client.readBeans();
//            for (MovieCsvRecord movie : movies) {
//                System.out.println(movie.getCollector());
//                System.out.println(movie.getUrl());
//                System.out.println(movie.getName());
//            }
//        }

        try(Reader reader = new InputStreamReader(spotifyAlbumsCsv.getInputStream())) {
            CsvClient<AlbumCsvRecord> client = new CsvClientImpl<>(reader, AlbumCsvRecord.class);
            List<AlbumCsvRecord> albums = client.readBeans();
            for (AlbumCsvRecord album : albums) {
                System.out.println(album.getAlbum());
                System.out.println(album.getArtist());
                System.out.println(album.getOwner());
                System.out.println(album.getWeb());
            }
        }

//        try(Reader reader = new InputStreamReader(amazonBooksCsv.getInputStream())) {
//            CsvClient<BookCsvRecord> client = new CsvClientImpl<>(reader, BookCsvRecord.class);
//            List<BookCsvRecord> books = client.readBeans();
//            for (BookCsvRecord book : books) {
//                System.out.println(book.getAuthor());
//                System.out.println(book.getTitle());
//                System.out.println(book.getWebsite());
//                System.out.println(book.getCollector());
//            }
//        }
//
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        amazonBooksCsv = resourceLoader.getResource("classpath:/import/amazon_books.csv");
        imdbMoviesCsv = resourceLoader.getResource("classpath:/import/imdb_movies.csv");
        spotifyAlbumsCsv = resourceLoader.getResource("classpath:/import/spotify_albums.csv");
    }

}
