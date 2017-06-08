package nl._42.qualityws.cleancode.collector;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorRepository extends JpaRepository<Collector, Long> {

    Collector findByName(String name);

}