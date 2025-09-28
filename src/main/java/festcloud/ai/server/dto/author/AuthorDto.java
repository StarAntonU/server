package festcloud.ai.server.dto.author;

import java.util.Set;
import lombok.Data;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private Set<Long> booksId;
}
