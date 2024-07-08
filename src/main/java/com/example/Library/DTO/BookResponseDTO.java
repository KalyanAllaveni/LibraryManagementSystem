package com.example.Library.DTO;

import com.example.Library.Entities.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    long book_id;
    String name;
    String author;
    String publication;
    String genre;

    public static BookResponseDTO toBookResponseDTO(Book book){
        return BookResponseDTO.builder()
        .author(book.getAuthor())
        .name(book.getName())
        .book_id(book.getBook_id())
        .genre(book.getGenre())
        .publication(book.getPublication())
        .build();
    }
}
