package nl._42.qualityws.cleancode.repository;

import nl._42.qualityws.cleancode.model.CollectorsItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorsItemRepository extends JpaRepository<CollectorsItem, Long> {

    CollectorsItem findByName(String name);

}
