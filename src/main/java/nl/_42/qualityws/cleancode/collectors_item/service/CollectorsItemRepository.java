package nl._42.qualityws.cleancode.collectors_item.service;

import org.springframework.data.jpa.repository.JpaRepository;

import nl._42.qualityws.cleancode.collectors_item.CollectorsItem;

interface CollectorsItemRepository extends JpaRepository<CollectorsItem, Long> {

    CollectorsItem findByName(String name);

}
