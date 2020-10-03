package ru.otus.spring.repositories;

import lombok.val;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa{

    @PersistenceContext
    private EntityManager em;

    @Transactional
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

    @Transactional
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void updateNameById(long id, String title) {
        Book book = em.find(Book.class, id);
        book.setTitle(title);
        em.merge(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        em.remove(em.find(Book.class, id));
    }

    @Transactional
    @Override
    public int countBooks() {
        List<Book> books = findAll();
        return books.size();
    }

    @Transactional
    @Override
    public void printAllBooks() {
        val books = findAll();
        printBooks(books);
    }

    @Override
    public void printBooks(List<Book> listBooks) {
        for (int i = 0; i<listBooks.size(); i++){
            System.out.println(listBooks.get(i).toString());
        }
    }

    @Transactional
    @Override
    public List<Book> getBooksByAuthor(List<Author> authors) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> bookRoot = criteria.from(Book.class);
        criteria.select(bookRoot).where(builder.isMember(authors, bookRoot.get("authors")));

        TypedQuery<Book> query = em.createQuery(criteria);
        List<Book> results = query.getResultList();

        if (results == null || results.size() == 0) {
            return null;
        } else {
            return results;
        }
    }

    @Transactional
    @Override
    public List<Book> getBooksByAuthorName(String name) {

        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        List<Author> authors =  query.getResultList();

        if (authors.size()>0) {
            val author = authors.get(0);
            return author.getBooks();
        }
        List<Book> books = new ArrayList<>();
        return books;
    }

    public Author getAuthorById(Long id){
        return em.find(Author.class, id);
    }
}
