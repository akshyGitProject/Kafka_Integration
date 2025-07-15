package com.example.bookAPIPractice2.Controller;

import com.example.bookAPIPractice2.Service.BookService;
import com.example.bookAPIPractice2.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/test")
    public String test(){

        return "This is Testinggg";
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){

        return bookService.getAllBook();
    }

    @GetMapping("/books/{bookid}")
    public Book getsingleBook(@PathVariable("bookid")int bookid){

        return bookService.getSingelbook(bookid);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book b){
        Book s=bookService.saveBook(b);
        return s;
    }

    @DeleteMapping("/books/{bookid}")
    public void deleteBook(@PathVariable("bookid")int bookid){
        bookService.deleteBook(bookid);
    }

    @PutMapping("/books/bookid")
    public void updateBook(@RequestBody Book book ,@PathVariable("bookid")int bookid){

        bookService.updateBook(book,bookid);
    }
}
