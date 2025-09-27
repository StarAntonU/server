package festcloud.ai.server.repository;

import festcloud.ai.server.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = "authors")
    Optional<Book> findById(Long bookId);

    @EntityGraph(attributePaths = "authors")
    List<Book> findAll();
}
