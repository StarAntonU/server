package festcloud.ai.server.dto.book;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record CreateBookDto(
        @NotBlank
        String title,
        Set<Long> authors
) {
}
