package com.library.system.librarysystem.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "genre")
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                '}';
    }
}
