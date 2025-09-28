package festcloud.ai.server.repository;

import festcloud.ai.server.model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = "books")
    Optional<Author> findById(Long id);

    @EntityGraph(attributePaths = "books")
    List<Author> findAll();
}
