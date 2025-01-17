package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable long id) {
        Book toReturn;
        for (Book book : this.books) {
            if (book.getId() == id) {
                toReturn = book;
                return toReturn;
            }
        } return null;

    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable long id, @RequestBody Book book) {
        Book toPut = null;
        for (Book writing : this.books) {
            if (writing.getId() == id) {
                writing.setTitle(book.getTitle());
                writing.setNumPages(book.getNumPages());
                writing.setAuthor(book.getAuthor());
                writing.setGenre(book.getGenre());
                //A book with a changed parameter is a new book technically speaking
                writing.recomputeID();
                toPut = writing;
            }
        } return toPut;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable long id) {
        Book toDelete;
        for (Book book : this.books) {
            if (book.getId() == id) {
                toDelete = book;
                books.remove(book);
                return toDelete;
            }
        } return null;

    }

}