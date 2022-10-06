package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mainPr.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.base.BaseDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Book")
public class Book extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @NotBlank
    private String bookTitle;
    private String bookSubTitle;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<BookImage> bookImgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Book.BookStatus bookStatus = BookStatus.BOOK_EXIST;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Diary> diary = new ArrayList<>();


    public void setMember(Member member) {
        this.member = member;
    }

    public enum BookStatus {
        BOOK_EXIST("존재하는 일기"),
        BOOK_NOT_EXIST("존재하지 않는 일기");

        @Getter
        private String status;

        BookStatus(String status) {
            this.status = status;
        }
    }

}
