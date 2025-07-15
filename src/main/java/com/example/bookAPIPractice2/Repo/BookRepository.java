package com.example.bookAPIPractice2.Repo;

import com.example.bookAPIPractice2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


    Optional<Book> findById(Integer id);


}
