package festcloud.ai.server.tool;

import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import festcloud.ai.server.service.BookService;
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
public class BookTool {
    private final BookService bookService;

    @Tool(name = "getAllBooks", description = "Get all books")
    @QueryMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @Tool(name = "createBook", description = "Create the book")
    @MutationMapping
    public BookDto createBook(@Valid @Argument CreateBookDto createDto) {
        return bookService.createBook(createDto);
    }

    @Tool(name = "updateBook", description = "Update the book")
    @MutationMapping
    public BookDto updateBook(@Valid @Argument UpdateBookDto updateDto) {
        return bookService.updateBook(updateDto);
    }

    @Tool(name = "deleteBook", description = "Delete the book")
    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}
