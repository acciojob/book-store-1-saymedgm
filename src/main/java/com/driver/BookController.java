package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        book.setId(id);
        id++;
        bookList.add(book);
        Book newbook = book;
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        Book bookWithId = null;
        for(Book book: bookList){
            if(book.getId() == Integer.parseInt(id)){
                bookWithId = book;
            }
        }
        return new ResponseEntity<>(bookWithId, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable String id){
        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getId() == Integer.parseInt(id)){
                bookList.remove(i);
            }
        }
        return new ResponseEntity<>("Book with id " + id + " deleted successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = this.bookList;
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks(){
        this.bookList = new ArrayList<Book>();
        return new ResponseEntity<>("All books deleted", HttpStatus.CREATED);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
        List<Book> booksByAuthor = new ArrayList<Book>();
        for(Book book : bookList){
            if(book.getAuthor().equals(author)){
                booksByAuthor.add(book);
            }
        }

        return new ResponseEntity<>(booksByAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre){
        List<Book> booksByGenre = new ArrayList<Book>();
        for(Book book : bookList){
            if(book.getGenre().equals(genre)){
                booksByGenre.add(book);
            }
        }

        return new ResponseEntity<>(booksByGenre, HttpStatus.CREATED);
    }
}