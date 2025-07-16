package com.example.bookAPIPractice2.Service;

import com.example.bookAPIPractice2.Repo.BookRepository;
import com.example.bookAPIPractice2.entity.Book;
import com.example.bookAPIPractice2.kafka.BookProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookProducer bookProducer;

//public static List<Book> list=new ArrayList<>();
//
//static {
//    list.add(new Book(1,"abc","Akshay"));
//    list.add(new Book(2,"xyz","vijay"));
//    list.add(new Book(3,"mno","sonakshi"));
//}

public List<Book> getAllBook(){

//    List<Book> list=
    return bookRepository.findAll();
}

public Book getSingelbook(int bookid){

   Optional<Book> book=  bookRepository.findById(bookid);
    return book.orElse(null);
}

//public Book saveBook(Book book){
//
//    Book b=bookRepository.save(book);
//    return b;
//}

    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        bookProducer.sendBook(savedBook);  // 🔥 send to Kafka
        return savedBook;
    }

public void deleteBook(int bookid){
   bookRepository.deleteById(bookid);

}

    public void updateBook(Book book,int bookid){

        book.setId(bookid);
        bookRepository.save(book);
    }

    //For Buld data
    public List<Book> saveBooks(List<Book> books) {
        List<Book> savedBooks = bookRepository.saveAll(books);
        for (Book book : savedBooks) {
            bookProducer.sendBook(book); // send each to Kafka
        }
        return savedBooks;
    }
}
