package ru.zinkin.app.marvel_superheroes_card.service.abstracts;

import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestComicsDto;

public interface AbstractComicsDtoService {
    boolean saveComicsDto(RequestComicsDto requestComicsDto);
}
