package nl._42.qualityws.cleancode.model.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl._42.qualityws.cleancode.model.CollectorsItem;

public abstract class AbstractCollectorsItemValidator<T extends CollectorsItem> implements CollectorsItemValidator<T> {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCollectorsItemValidator.class);

    @Override
    public final boolean validate(T item) {
        List<ValidationError> errors = validateItem(item);
        if (!errors.isEmpty()) {
            logErrorHeader(item);
            errors.forEach(error -> LOGGER.error("- {}", error.getDescription()));
        }
        return errors.isEmpty();
    }

    protected abstract List<ValidationError> validateItem(T item);

    protected abstract void logErrorHeader(T item);

}
