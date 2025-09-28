package festcloud.ai.server.service;

import festcloud.ai.server.dto.author.AuthorDto;
import festcloud.ai.server.dto.author.CreateAuthorDto;
import festcloud.ai.server.dto.author.UpdateAuthorDto;
import java.util.List;

public interface AuthorService {
    AuthorDto createAuthor(CreateAuthorDto createDto);

    AuthorDto updateAuthor(UpdateAuthorDto updateDto);

    Boolean deleteAuthor(Long id);

    List<AuthorDto> getAllAuthors();
}
