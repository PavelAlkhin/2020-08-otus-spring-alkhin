package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryJpaImlp implements AuthorRepositoryJpa {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a", Author.class);
        return query.getResultList();    }

    @Transactional
    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Book> findByAuthorName(String name) {
        List<Author> authorList = findByName(name);
        if (authorList.size()>0){
            Author author = authorList.get(0);
            return author.getBooks();
        }
        return new ArrayList<>();
    }

    @Override
    public String printBooksByAuthorName(String name) {
        List<Author> authorList = findByName(name);
        if (authorList.size()>0){
            Author author = authorList.get(0);
            return author.printBooks();
        }
        return "no books";
    }


}
