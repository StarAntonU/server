package festcloud.ai.server.dto.author;

import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class CreateAuthorDto {
    @NotBlank
    private String name;
    private Set<Long> booksId = new HashSet<>();
}
