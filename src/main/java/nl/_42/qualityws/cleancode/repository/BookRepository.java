package nl._42.qualityws.cleancode.repository;

import nl._42.qualityws.cleancode.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
