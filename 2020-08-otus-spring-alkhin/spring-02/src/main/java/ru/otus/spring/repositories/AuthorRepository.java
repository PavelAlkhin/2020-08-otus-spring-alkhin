package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    @PostFilter("hasPermission(filterObject, 'READ')")
    Author findByName(String name);

//    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Author> findAllByIdIn(List<Long> ids);

    //@SuppressWarnings("unchecked")
//    @PreAuthorize("hasPermission(#author, 'WRITE')")
    Author save(@Param("author") Author author);

}
