package festcloud.ai.server.dto.author;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record CreateAuthorDto(
        @NotBlank
        String name,
        Set<Long> booksId
) {
}
