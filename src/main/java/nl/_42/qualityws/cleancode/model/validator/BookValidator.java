package nl._42.qualityws.cleancode.model.validator;

import java.util.ArrayList;
import java.util.List;

import nl._42.qualityws.cleancode.model.Book;

import org.springframework.stereotype.Component;

import com.sun.javafx.binding.StringFormatter;

@Component
public class BookValidator extends AbstractPersonNameValidator<Book> {

    @Override
    protected List<ValidationError> validateItem(Book book) {
        final List<ValidationError> errors = new ArrayList<>();
        if (!book.getAmazonUrl().startsWith("https://www.amazon.com/dp/")) {
            errors.add(new ValidationError(StringFormatter.format("illegal Amazon URL %s", book.getAmazonUrl()).getValue()));
        }
        if (!verifyName(book.getAuthor())) {
            errors.add(new ValidationError(StringFormatter.format("illegal Book author [%s]", book.getAuthor()).getValue()));
        }
        return errors;
    }

    @Override
    protected void logErrorHeader(Book book) {
        LOGGER.error("Errors for book [{}] with author [{}]", book.getName(), book.getAuthor());
    }

}
