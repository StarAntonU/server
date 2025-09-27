package festcloud.ai.server.service;

import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import java.util.List;

public interface BookService {
    BookDto createBook(CreateBookDto createDto);

    BookDto updateBook(UpdateBookDto updateDto);

    Boolean deleteBook(Long bookId);

    List<BookDto> getAll();
}
