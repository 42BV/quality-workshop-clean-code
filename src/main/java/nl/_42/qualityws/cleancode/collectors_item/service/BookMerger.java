package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMerger extends AbstractCollectorsItemMerger<Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    protected boolean validate(Book book) {
        return book.getAmazonUrl().startsWith("https://www.amazon.com/dp/");
    }

    @Override
    protected void logValidateError(Book book) {
        LOGGER.error("Illegal Amazon URL {} for book [{}]", book.getAmazonUrl(), book.getName());
    }

    @Override
    protected Book lookupItem(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    protected Book save(Book book) {
        return bookRepository.save(book);
    }

}
