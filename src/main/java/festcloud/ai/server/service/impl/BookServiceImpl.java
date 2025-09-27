package festcloud.ai.server.service.impl;

import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import festcloud.ai.server.exception.EntityNotFindException;
import festcloud.ai.server.mapper.BookMapper;
import festcloud.ai.server.model.Book;
import festcloud.ai.server.repository.BookRepository;
import festcloud.ai.server.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto createBook(CreateBookDto createDto) {
        Book book = bookMapper.toModel(createDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBook(UpdateBookDto updateDto) {
        Book book = findBookById(updateDto.id());
        bookMapper.updateBook(book, updateDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Boolean deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toDto).toList();
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFindException("Can`t find the book by id " + bookId)
        );
    }
}
