package nl._42.qualityws.cleancode.collectors_item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nl._42.qualityws.cleancode.collector.Collector;
import nl._42.qualityws.cleancode.collector.builder.CollectorBuilder;
import nl._42.qualityws.cleancode.collectors_item.builder.CollectorsItemBuilder;
import nl._42.qualityws.cleancode.shared.AbstractIntegrationTest;

public class MovieRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CollectorBuilder collectorBuilder;
    @Autowired
    private CollectorsItemBuilder collectorsItemBuilder;

    @Test
    public void findOne_shouldSucceed_afterEntitySaved() {
        final Collector collector = collectorBuilder.collector("Cornelis de Verzamelaar").save();
        final String expectedName = "The Wire";
        final String expectedImdbUrl = "http://www.imdb.com/title/tt0306414/";
        
        Movie movie = collectorsItemBuilder.movie(expectedName, collector)
                .withImdbUrl(expectedImdbUrl).save();
        
        movie = movieRepository.findOne(movie.getId());
        assertEquals(expectedName, movie.getName());
        assertEquals(expectedImdbUrl, movie.getImdbUrl());
        assertEquals(expectedImdbUrl, movie.getImdbUrl());
        assertEquals(collector.getName(), movie.getCollector().getName());
    }

}