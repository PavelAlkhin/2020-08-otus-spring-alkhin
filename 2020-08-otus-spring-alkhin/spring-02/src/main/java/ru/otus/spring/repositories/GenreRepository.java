package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.models.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

//    @PostFilter("hasPermission(filterObject, 'READ')")
    Genre findByName(String name);

//    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Genre> findAllByIdIn(List<Long> id);

//    @SuppressWarnings("unchecked")
//    @PreAuthorize("hasPermission(#noticeMessage, 'WRITE')")
    Genre save(@Param("genre") Genre genre);

}
