package nl._42.qualityws.cleancode.collectors_item;

import org.springframework.data.jpa.repository.JpaRepository;

interface AlbumRepository extends JpaRepository<Album, Long> {
}
