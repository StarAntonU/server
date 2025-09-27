package festcloud.ai.server.dto.book;

import java.util.Set;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private Set<Long> authorsId;
}
