package nl._42.qualityws.cleancode.model.validator;

import nl._42.qualityws.cleancode.model.CollectorsItem;

public interface CollectorsItemValidator<T extends CollectorsItem> {

    boolean validate(T item);

}
