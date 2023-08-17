package ru.zinkin.app.marvel_superheroes_card.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidErrorResponse {
    private final List<Violation> violations;
}
