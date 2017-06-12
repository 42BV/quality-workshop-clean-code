package nl._42.qualityws.cleancode.repository;

import nl._42.qualityws.cleancode.model.Collector;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorRepository extends JpaRepository<Collector, Long> {

    Collector findByName(String name);

}