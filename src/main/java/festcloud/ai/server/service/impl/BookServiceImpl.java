package festcloud.ai.server.service.impl;

import festcloud.ai.server.dto.book.BookDto;
import festcloud.ai.server.dto.book.CreateBookDto;
import festcloud.ai.server.dto.book.UpdateBookDto;
import festcloud.ai.server.exception.EntityNotFindException;
import festcloud.ai.server.mapper.BookMapper;
import festcloud.ai.server.model.Book;
import festcloud.ai.server.repository.AuthorRepository;
import festcloud.ai.server.repository.BookRepository;
import festcloud.ai.server.service.BookService;
import jakarta.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    @Override
    public BookDto createBook(CreateBookDto createDto) {
        if (isBookExists(createDto.getTitle())) {
            throw new EntityExistsException(String.format("Book with title %s is exists",
                    createDto.getTitle()));
        }
        if (!createDto.getAuthorsId().isEmpty()) {
            Set<Long> setAuthors = createDto.getAuthorsId()
                    .stream()
                    .filter(authorRepository::existsById)
                    .collect(Collectors.toSet());
            if (setAuthors.isEmpty()) {
                throw new EntityNotFindException("Authors with ids are not exists "
                        + createDto.getAuthorsId());
            }
            createDto.setAuthorsId(setAuthors);
        }
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
        return books
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFindException("Can`t find the book by id " + bookId)
        );
    }

    private Boolean isBookExists(String title) {
        return bookRepository.existsByTitle(title);
    }
}
