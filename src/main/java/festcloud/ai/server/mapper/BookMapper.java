package festcloud.ai.server.mapper;

import festcloud.ai.server.config.MapperConfig;
import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import festcloud.ai.server.model.Author;
import festcloud.ai.server.model.Book;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "authors", ignore = true)
    Book toModel(CreateBookDto createBookDto);

    @AfterMapping
    default void setAuthors(@MappingTarget Book book, CreateBookDto createDto) {
        Set<Author> authors = createDto.getAuthorsId()
                .stream()
                .map(Author::new)
                .collect(Collectors.toSet());
        book.setAuthors(authors);
    }

    @Mapping(target = "authorsId", ignore = true)
    BookDto toDto(Book book);

    @AfterMapping
    default void setAuthorsId(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> authorsId = book.getAuthors()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toSet());
        bookDto.setAuthorsId(authorsId);
    }

    void updateBook(@MappingTarget Book book, UpdateBookDto updateDto);
}
