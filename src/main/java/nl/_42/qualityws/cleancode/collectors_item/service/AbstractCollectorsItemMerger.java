package nl._42.qualityws.cleancode.collectors_item.service;

import java.util.Collection;

import io.beanmapper.BeanMapper;
import nl._42.qualityws.cleancode.collectors_item.Book;
import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCollectorsItemMerger<T extends CollectorsItem> implements CollectorsItemMerger<T> {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractCollectorsItemMerger.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CollectorsItemRepository repository;

    @Override
    public void merge(Collection<T> items) {
        for (T item : items) {
            if (!validate(item)) {
                logValidateError(item);
                continue;
            }
            T foundItem = lookupItem(item.getName());
            if (foundItem != null) {
                LOGGER.info("Item already exists [{}], merging", item.getName());
                item = beanMapper.map(item, foundItem);
            }
            save(item);
        }
    }

    protected abstract boolean validate(T item);

    protected abstract void logValidateError(T item);

    protected abstract T lookupItem(String name);

    protected abstract T save(T item);

}
