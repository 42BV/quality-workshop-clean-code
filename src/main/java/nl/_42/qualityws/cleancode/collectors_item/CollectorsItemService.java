package nl._42.qualityws.cleancode.collectors_item;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class CollectorsItemService {

    @Autowired
    private MovieRepository movieRepository;
    
    Movie createMovie(Movie movie) {
        notNull(movie, "Movie to create may not be null");
        isTrue(movie.isNew(), "Cannot create existing movie");
        return movieRepository.save(movie);
    }
    
    Page<Movie> listMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }
    
    
}
