package ru.zinkin.app.marvel_superheroes_card.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {

    private final String field;
    private final String message;

}
