package festcloud.ai.server.service.impl;

import festcloud.ai.server.repository.AuthorRepository;
import festcloud.ai.server.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
}
