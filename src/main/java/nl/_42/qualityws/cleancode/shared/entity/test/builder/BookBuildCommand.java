package nl._42.qualityws.cleancode.shared.entity.test.builder;

import nl._42.beanie.EditableBeanBuildCommand;
import nl._42.qualityws.cleancode.collector.Collector;
import nl._42.qualityws.cleancode.collectors_item.Book;

public interface BookBuildCommand extends EditableBeanBuildCommand<Book> {
    
    BookBuildCommand withName(String name);
    BookBuildCommand withCollector(Collector collector);
    BookBuildCommand withAmazonUrl(String amazonUrl);
}
