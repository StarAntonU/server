package festcloud.ai.server.mapper;

import festcloud.ai.server.config.MapperConfig;
import festcloud.ai.server.dto.author.AuthorDto;
import festcloud.ai.server.dto.author.CreateAuthorDto;
import festcloud.ai.server.dto.author.UpdateAuthorDto;
import festcloud.ai.server.model.Author;
import festcloud.ai.server.model.Book;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface AuthorMapper {
    @Mapping(target = "books", ignore = true)
    Author toModel(CreateAuthorDto createDto);

    @AfterMapping
    default void setBooks(@MappingTarget Author author, CreateAuthorDto createDto) {
        Set<Book> books = createDto.booksId()
                .stream()
                .map(Book::new)
                .collect(Collectors.toSet());
        author.setBooks(books);
    }

    @Mapping(target = "booksId", ignore = true)
    AuthorDto toDto(Author author);

    @AfterMapping
    default void setBooksId(@MappingTarget AuthorDto authorDto, Author author) {
        Set<Long> booksId = author.getBooks()
                .stream()
                .map(Book::getId)
                .collect(Collectors.toSet());
        authorDto.setBooksId(booksId);
    }

    void update(@MappingTarget Author author, UpdateAuthorDto updateDto);
}
