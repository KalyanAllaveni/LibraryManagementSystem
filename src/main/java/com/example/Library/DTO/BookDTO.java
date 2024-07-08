package com.example.Library.DTO;
import com.example.Library.Entities.Book;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDTO {
    String name;
    String author;
    String publication;
    String genre;

    public Book convertToBook(BookDTO bookDTO){
        Book book =new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        book.setPublication(bookDTO.getPublication());
        book.setGenre(bookDTO.getGenre());
        return book;
    }

   

    
}
