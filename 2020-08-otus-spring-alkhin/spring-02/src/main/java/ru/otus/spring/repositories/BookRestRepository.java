//package ru.otus.spring.repositories;
//
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
//import ru.otus.spring.models.Book;
//
//import java.util.List;
//
//@RepositoryRestResource(path="datarest")
//public interface BookRestRepository extends PagingAndSortingRepository<Book, Long>{
//
//    @RestResource(path = "title", rel = "title")
//    List<Book> findByTitle(String title);
//
//    @RestResource(path = "all", rel = "all")
//    List<Book> findAll();
//
//}
