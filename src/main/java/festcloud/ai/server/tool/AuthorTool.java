package festcloud.ai.server.tool;

import festcloud.ai.server.dto.author.AuthorDto;
import festcloud.ai.server.dto.author.CreateAuthorDto;
import festcloud.ai.server.dto.author.UpdateAuthorDto;
import festcloud.ai.server.service.AuthorService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorTool {
    private final AuthorService authorService;

    @Tool(name = "getAllAuthors", description = "Get all authors")
    @QueryMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @Tool(name = "createAuthor", description = "Create the author")
    @MutationMapping
    public AuthorDto createAuthor(@Argument @Valid CreateAuthorDto createDto) {
        return authorService.createAuthor(createDto);
    }

    @Tool(name = "updateAuthor", description = "Update the author")
    @MutationMapping
    public AuthorDto updateAuthor(@Argument @Valid UpdateAuthorDto updateDto) {
        return authorService.updateAuthor(updateDto);
    }

    @Tool(name = "deleteAuthor", description = "Delete the author")
    @MutationMapping
    public Boolean deleteAuthor(@Argument Long id) {
        return authorService.deleteAuthor(id);
    }

}
