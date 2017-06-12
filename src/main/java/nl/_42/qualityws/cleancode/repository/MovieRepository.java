package nl._42.qualityws.cleancode.repository;

import nl._42.qualityws.cleancode.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
