package nl._42.qualityws.cleancode.shared.entity.test.builder;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestDataLoader implements ResourceLoaderAware {

    private Resource amazonBooksCsv;
    private Resource imdbMoviesCsv;
    private Resource spotifyAlbumsCsv;
    
    @PostConstruct
    public void load() throws IOException {
        System.out.println(amazonBooksCsv.contentLength());
        System.out.println(imdbMoviesCsv.contentLength());
        System.out.println(spotifyAlbumsCsv.contentLength());
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        amazonBooksCsv = resourceLoader.getResource("classpath:/import/amazon_books.csv");
        imdbMoviesCsv = resourceLoader.getResource("classpath:/import/imdb_movies.csv");
        spotifyAlbumsCsv = resourceLoader.getResource("classpath:/import/spotify_albums.csv");
    }
}
