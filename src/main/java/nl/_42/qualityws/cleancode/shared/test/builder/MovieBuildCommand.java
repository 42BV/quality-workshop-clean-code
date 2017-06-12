package nl._42.qualityws.cleancode.shared.test.builder;

import nl._42.beanie.EditableBeanBuildCommand;
import nl._42.qualityws.cleancode.model.Collector;
import nl._42.qualityws.cleancode.model.Movie;

public interface MovieBuildCommand extends EditableBeanBuildCommand<Movie> {
    
    MovieBuildCommand withName(String name);
    MovieBuildCommand withCollector(Collector collector);
    MovieBuildCommand withImdbUrl(String imdbUrl);
}
