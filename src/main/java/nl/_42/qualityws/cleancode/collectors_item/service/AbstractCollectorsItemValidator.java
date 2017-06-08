package nl._42.qualityws.cleancode.collectors_item.service;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractCollectorsItemValidator<T extends CollectorsItem> implements CollectorsItemValidator<T> {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCollectorsItemValidator.class);

    @Override
    public boolean validate(T item) {
        boolean validationResult = validateItem(item);
        if (!validationResult) {
            logError(item);
        }
        return validationResult;
    }

    protected abstract boolean validateItem(T item);

    protected abstract void logError(T item);

}
