package com.example.Library.Services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Library.DTO.BookDTO;
import com.example.Library.DTO.BookResponseDTO;
import com.example.Library.Entities.Book;
import com.example.Library.Repository.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookResponseDTO getBooks(long id) {
        try {
            return BookResponseDTO.toBookResponseDTO(bookRepository.findById(id).get());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book is not present");
        }
    }

    public Book getBook(long id) {
        try {
            return bookRepository.findById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book is not present");
        }
    }

    public List<Book> getAllBooksById(long[] ids) {
        return null;
    }

    public List<Book> getBookDetailsByAuthorName(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookDetailsByBookName(String name) {
        return bookRepository.findByName(name);
    }

    public Book saveBookDtoInfo(BookDTO bookdto) {
        Book book = bookdto.convertToBook(bookdto);
        return bookRepository.save(book);
    }

    public Book updateBook(long id, BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book b = book.get();
            if (bookDTO.getName() != null) {
                b.setName(bookDTO.getName());
            }

            if (bookDTO.getAuthor() != null) {
                b.setAuthor(bookDTO.getAuthor());
            }
            if (bookDTO.getPublication() != null) {
                b.setPublication(bookDTO.getPublication());
            }
            if (bookDTO.getGenre() != null) {
                b.setGenre(bookDTO.getGenre());
            }
            this.bookRepository.save(b);
            return b;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "book is not present");
    }

    public String deleteBookDetailsById(long id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

    public List<Book> getBookByPublication(String value) {
        return bookRepository.findByPublication(value);
    }

    public List<Book> getBookByGenre(String value) {
        return bookRepository.findByGenre(value);
    }

    public void savebook(Book book) {
        bookRepository.save(book);
    }

}
