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
    
    @RequestMapping(path = "/movies", method = RequestMethod.POST)
    public MovieResult createMovie(@Valid @MergedForm(value = MovieForm.class) Movie movie) {
        return beanMapper.map(itemService.createMovie(movie), MovieResult.class);
    }
}
