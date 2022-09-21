package it.mainPr.book.entity;

import it.mainPr.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "BOOK")
@AllArgsConstructor
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private String subtitle;

    private String img;

//    private boolean secret;
}
