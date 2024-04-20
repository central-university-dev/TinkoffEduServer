package com.tinkoffedu.service;

import com.tinkoffedu.dto.quiz.QuizRequest;
import com.tinkoffedu.entity.Quiz;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.QuizMapper;
import com.tinkoffedu.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository repository;
    private final QuizMapper mapper;

    @Transactional
    public Quiz createQuiz(QuizRequest dto) {
        validateRequest(dto);

        return repository.save(mapper.map(dto));
    }

    @Transactional
    public void updateQuiz(QuizRequest dto) {
        validateRequest(dto);

        repository.findById(dto.id()).orElseThrow(
            () -> new NotFoundException(Quiz.class)
        );
        repository.save(mapper.map(dto));
    }

    // TODO: сделать нормальную валидацию
    private void validateRequest(QuizRequest dto) {
        if (dto.name().length() > 64 || dto.description().length() > 256) {
            throw new InvalidArgumentException(
                "name should contain less than 64 chars and description should contain less than 256 chars"
            );
        }
    }

}
