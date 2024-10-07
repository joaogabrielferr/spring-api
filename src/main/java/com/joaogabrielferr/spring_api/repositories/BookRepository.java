package com.joaogabrielferr.spring_api.repositories;

import com.joaogabrielferr.spring_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
