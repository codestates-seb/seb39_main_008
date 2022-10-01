package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    private String bookTitle;
    private String bookSubTitle;
    private String bookImageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Diary> diary = new ArrayList<>();

    @Builder
    public Book(String bookTitle, String bookSubTitle, String bookImageUrl, Member member) {
        this.bookTitle = bookTitle;
        this.bookSubTitle = bookSubTitle;
        this.bookImageUrl = bookImageUrl;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
