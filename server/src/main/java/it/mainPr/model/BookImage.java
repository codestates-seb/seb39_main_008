package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOK_IMAGE")
public class BookImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookImageId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private BookImageStatus bookImageStatus = BookImageStatus.BOOK_IMAGE_EXIST;

    private String bookImgUrl;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public enum BookImageStatus {
        BOOK_IMAGE_EXIST("북이 존재합니다"),
        BOOK_IMAGE_NOT_EXIST("북이 존재하지않습니다");

        @Getter
        private String status;

        BookImageStatus(String status) {
            this.status = status;
        }
    }
}
