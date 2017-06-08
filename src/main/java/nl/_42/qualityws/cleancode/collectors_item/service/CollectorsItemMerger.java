package nl._42.qualityws.cleancode.collectors_item.service;

import java.util.Collection;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

public interface CollectorsItemMerger<T extends CollectorsItem> {

    void merge(Collection<T> items);

}
