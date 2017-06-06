package nl._42.qualityws.cleancode.collectors_item;

import static org.junit.Assert.assertEquals;

import nl._42.qualityws.cleancode.collector.Collector;
import nl._42.qualityws.cleancode.collector.CollectorRepository;
import nl._42.qualityws.cleancode.collectors_item.Movie;
import nl._42.qualityws.cleancode.collectors_item.MovieRepository;
import nl._42.qualityws.cleancode.shared.AbstractIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void create() {
        final Collector collector = new Collector();
        collector.setName("Cornelis de Verzamelaar");
        collectorRepository.save(collector);
        final String expectedName = "The Wire";
        final String expectedImdbUrl = "http://www.imdb.com/title/tt0306414/";
        Movie movie = new Movie();
        movie.setName(expectedName);
        movie.setCollector(collector);
        movie.setImdbUrl(expectedImdbUrl);
        movieRepository.save(movie);
        movie = movieRepository.findOne(movie.getId());
        assertEquals(expectedName, movie.getName());
        assertEquals(expectedImdbUrl, movie.getImdbUrl());
        assertEquals(expectedImdbUrl, movie.getImdbUrl());
        assertEquals(collector.getName(), movie.getCollector().getName());
    }

}
