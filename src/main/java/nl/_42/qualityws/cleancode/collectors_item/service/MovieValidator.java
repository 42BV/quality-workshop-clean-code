package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Movie;

import org.springframework.stereotype.Component;

@Component
class MovieValidator extends AbstractCollectorsItemValidator<Movie> {

    @Override
    protected boolean validateItem(Movie movie) {
        return movie.getImdbUrl().startsWith("http://www.imdb.com/title/");
    }

    @Override
    protected void logError(Movie movie) {
        LOGGER.error("Illegal IMDB URL {} for movie [{}]", movie.getImdbUrl(), movie.getName());
    }

}
