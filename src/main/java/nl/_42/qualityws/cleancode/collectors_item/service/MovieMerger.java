package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMerger extends AbstractCollectorsItemMerger<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    protected boolean validate(Movie movie) {
        return movie.getImdbUrl().startsWith("http://www.imdb.com/title/");
    }

    @Override
    protected void logValidateError(Movie movie) {
        LOGGER.error("Illegal IMDB URL {} for movie [{}]", movie.getImdbUrl(), movie.getName());
    }

    @Override
    protected Movie lookupItem(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    protected Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

}
