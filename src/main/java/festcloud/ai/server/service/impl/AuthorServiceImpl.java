package festcloud.ai.server.service.impl;

import festcloud.ai.server.dto.author.AuthorDto;
import festcloud.ai.server.dto.author.CreateAuthorDto;
import festcloud.ai.server.dto.author.UpdateAuthorDto;
import festcloud.ai.server.exception.EntityNotFindException;
import festcloud.ai.server.mapper.AuthorMapper;
import festcloud.ai.server.model.Author;
import festcloud.ai.server.repository.AuthorRepository;
import festcloud.ai.server.service.AuthorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDto createAuthor(CreateAuthorDto createDto) {
        Author author = authorMapper.toModel(createDto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public AuthorDto updateAuthor(UpdateAuthorDto updateDto) {
        Author author = getAuthorById(updateDto.id());
        authorMapper.update(author, updateDto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    @Override
    public Boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    private Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(
                () -> new EntityNotFindException("Can`t find the Author by id" + id)
        );
    }
}
