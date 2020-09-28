package ru.otus.spring.repositories;

import lombok.val;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository(value="BookRepository")
public class BookRepositoryJpaImpl implements BookRepositoryJpa{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if(book.getId()==0){
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class,id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b", Book.class);
        return query.getResultList();

    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String title) {
        Query query = em.createQuery("update Book b set b.title = :title where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int countBooks() {
        List<Book> books = findAll();
        return books.size();
    }

    @Override
    public void printAllBooks() {
        val iterBooks = findAll().iterator();

        while (iterBooks.hasNext()){
            System.out.println(iterBooks.next().toString());
        }
    }
}
