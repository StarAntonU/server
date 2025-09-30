package festcloud.ai.server.dto.book;

import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class CreateBookDto {
    @NotBlank
    private String title;
    private Set<Long> authorsId = new HashSet<>();
}
