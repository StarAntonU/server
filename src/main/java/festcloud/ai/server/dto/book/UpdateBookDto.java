package festcloud.ai.server.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateBookDto(
        @Positive
        @NotNull
        Long id,
        @NotBlank
        String title
) {
}
