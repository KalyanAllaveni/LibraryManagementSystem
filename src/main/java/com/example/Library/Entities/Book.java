package com.example.Library.Entities;

import java.util.List;

import com.example.Library.Enum.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long book_id;
    String name;
    String author;
    String publication;
    String genre;
    @Enumerated(value = EnumType.STRING)
    BookStatus status;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    List<Request> request;

}
