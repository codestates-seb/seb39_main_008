package it.mainPr.repository;

import it.mainPr.model.Book;
import it.mainPr.model.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {

    List<BookImage> findAllByBookAndBookImageStatus(Book book, BookImage.BookImageStatus bookImageStatus);
}
