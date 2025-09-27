package festcloud.ai.server.controller;

import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import festcloud.ai.server.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @QueryMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @MutationMapping
    public BookDto createBook(@Valid @Argument CreateBookDto createDto) {
        return bookService.createBook(createDto);
    }

    @MutationMapping
    public BookDto updateBook(@Valid @Argument UpdateBookDto updateDto) {
        return bookService.updateBook(updateDto);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}
