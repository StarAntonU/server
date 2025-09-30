package festcloud.ai.server.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateAuthorDto(
        @Positive
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
