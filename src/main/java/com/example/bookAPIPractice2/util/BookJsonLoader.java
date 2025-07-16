package com.example.bookAPIPractice2.util;

import com.example.bookAPIPractice2.entity.Book;
import com.example.bookAPIPractice2.kafka.BookProducer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Component
public class BookJsonLoader {

    @Autowired
    private BookProducer bookProducer;

    @PostConstruct
    public void loadAndSendBooks() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("books_100.json").getInputStream();
            List<Book> books = mapper.readValue(inputStream, new TypeReference<List<Book>>() {});
            for (Book book : books) {
                bookProducer.sendBook(book);
                Thread.sleep(100); // optional delay
            }
            System.out.println("✅ All books from JSON sent to Kafka.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
