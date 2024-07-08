package com.example.Library.Controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.BookDTO;
import com.example.Library.DTO.BookResponseDTO;
import com.example.Library.Entities.Book;
import com.example.Library.Services.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public Book postMethodName(@RequestBody BookDTO bookdto) {
       return bookService.saveBookDtoInfo(bookdto);
    }
    
    @GetMapping("/book/{id}")
    public BookResponseDTO getBookName(@PathVariable long id) {
        return bookService.getBooks(id);
    }

    @GetMapping("/books/{id}")
    public Book getBooksName(@PathVariable long id) {
        return bookService.getBook(id);
    }


    @PutMapping("/book/update/{id}")
    public Book putMethodName(@PathVariable long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook
                (id,bookDTO);
    }
    
    @DeleteMapping("/book/delete/{id}")
    public String deleteBookById(@PathVariable long id){
        return bookService.deleteBookDetailsById(id);
    }

    @GetMapping("/book/get")
    public List<Book> getMethodName(@RequestParam("type") String type, @RequestParam("value") String value) {

        System.out.println(type);
        System.out.println(value);
        switch (type) {
            case "id" -> {
                ArrayList<Book> book = new ArrayList<>();
                Book book2 = this.bookService.getBook(Long.valueOf(value));
                book.add(book2);
                return book;
            }
            case "author" -> {
                return this.bookService.getBookDetailsByAuthorName(value);
            }
            case "name" -> {
                return this.bookService.getBookDetailsByBookName(value);
            }
            case "publication" -> {
                return this.bookService.getBookByPublication(value);
            }
            case "genre" -> {
                return this.bookService.getBookByGenre(value);
            }
            default -> throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Invalid type");
        }
        
    }
    
}
