package festcloud.ai.server.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateBookDto(
        @Positive
        Long id,
        @NotBlank
        String title
) {
}
