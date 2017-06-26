package nl._42.qualityws.cleancode.collectors_item.validator;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

interface CollectorsItemValidator<T extends CollectorsItem> {

    boolean validate(T item);

}
