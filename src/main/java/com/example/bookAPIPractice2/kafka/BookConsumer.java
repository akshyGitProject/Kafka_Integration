package com.example.bookAPIPractice2.kafka;

import com.example.bookAPIPractice2.entity.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookConsumer {

    @KafkaListener(topics = "book-topic", groupId = "book-group")
    public void consume(Book book) {
        System.out.println("📥 Received Book from Kafka → " + book.getTitle() + " by " + book.getAuthor());
    }
}