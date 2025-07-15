package com.example.bookAPIPractice2.Service;

import com.example.bookAPIPractice2.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookProducer {

    private static final String TOPIC = "book-topic";

    @Autowired
    private KafkaTemplate<String, Book> kafkaTemplate;

    public void sendBook(Book book) {
        kafkaTemplate.send(TOPIC, book);
    }
}
