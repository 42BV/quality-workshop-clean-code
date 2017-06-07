package nl._42.qualityws.cleancode.collectors_item.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl._42.beanie.BeanBuilder;
import nl._42.qualityws.cleancode.collector.Collector;

@Component
public class CollectorsItemBuilder {

    @Autowired
    private BeanBuilder beanBuilder;
    
    public MovieBuildCommand movie(String name, Collector collector) {
        return beanBuilder.startAs(MovieBuildCommand.class).withName(name).withCollector(collector);
    }
    
    public AlbumBuildCommand album(String name, Collector collector) {
        return beanBuilder.startAs(AlbumBuildCommand.class).withName(name).withCollector(collector);
    }
    
    public BookBuildCommand book(String name, Collector collector) {
        return beanBuilder.startAs(BookBuildCommand.class).withName(name).withCollector(collector);
    }
    
}
