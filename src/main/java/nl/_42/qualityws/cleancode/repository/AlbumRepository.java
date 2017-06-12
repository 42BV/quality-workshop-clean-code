package nl._42.qualityws.cleancode.repository;

import nl._42.qualityws.cleancode.model.Album;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
