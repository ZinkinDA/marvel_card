package ru.zinkin.app.marvel_superheroes_card.service.abstracts;

import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestCharacterDto;

public interface AbstractCharacterDtoService {
    boolean saveDto(RequestCharacterDto character);
}
