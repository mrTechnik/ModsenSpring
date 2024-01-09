package library.app.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import library.app.domain.Book;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<Book, Integer>{
    @Query(value = "select b.* from book b where b.isbn = :isbn",
                nativeQuery = true)
    List<Book> findByIsbn(String isbn);

    @Query(value = "select b.* from book b where b.isbn = :isbn limit first",
                nativeQuery = true)
    Book findByIsbnFirst(String isbn);
}
