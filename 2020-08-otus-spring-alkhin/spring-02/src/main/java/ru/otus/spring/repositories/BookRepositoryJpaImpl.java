package ru.otus.spring.repositories;

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
        EntityGraph<?> entityGraph = em.getEntityGraph("avatars-entuty-graph");
        TypedQuery<Book> query = em.createQuery("select b from books b join fetch b.genres", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();

    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b from books b where b.title = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String title) {
        Query query = em.createQuery("update books b set b.title = :title where b.id = :id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete books b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
