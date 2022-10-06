package it.mainPr.service;

import it.mainPr.model.Book;
import it.mainPr.model.BookImage;
import it.mainPr.repository.BookImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookImageService {

    BookImageRepository bookImageRepository;

    public List<BookImage> findVerifiedBookImages(Book book) {
        List<BookImage> findBookImages = bookImageRepository.findAllByBookAndBookImageStatus(
                book, BookImage.BookImageStatus.BOOK_IMAGE_EXIST);

        return findBookImages;
    }

}
