package com.example.Library.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library.Entities.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

    // @Query("select b from Book b where b.author= :author")
    List<Book> findByAuthor(String author);

    // @Query("select b from Book b where b.name= :name")
    List<Book> findByName(String name);

    // @Query("select b from Book b where b.publication=:publication")
    List<Book> findByPublication(String publication);

    // @Query("select b from Book b where b.genre=:genre")
    List<Book> findByGenre(String genre);



}
