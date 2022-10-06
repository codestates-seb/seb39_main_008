package it.mainPr.repository;

import it.mainPr.model.Book;
import it.mainPr.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findByMember(Member member);

    Optional<Book> findByBookTitleAndBookStatus(String bookTitle, Book.BookStatus bookStatus);

    List<Book> findByBookIdAndBookStatus(long bookId, Book.BookStatus bookStatus);
}