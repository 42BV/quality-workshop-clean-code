package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.Book;

import org.springframework.stereotype.Component;

@Component
class BookValidator extends AbstractCollectorsItemValidator<Book> {

    @Override
    protected boolean validateItem(Book book) {
        return book.getAmazonUrl().startsWith("https://www.amazon.com/dp/");
    }

    @Override
    protected void logError(Book book) {
        LOGGER.error("Illegal Amazon URL {} for book [{}]", book.getAmazonUrl(), book.getName());
    }

}
