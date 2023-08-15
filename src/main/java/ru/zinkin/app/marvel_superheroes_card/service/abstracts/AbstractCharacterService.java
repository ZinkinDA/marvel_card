package ru.zinkin.app.marvel_superheroes_card.service.abstracts;

import org.springframework.data.domain.Page;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestCharacterDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import java.util.Map;
import java.util.Optional;

public interface AbstractCharacterService extends AbstractCharacterDtoService {
    Page<Characters> getAll(Map<String, Object> claims);

    Optional<Characters> findById(String id);

    Page<Comics> findComicsByCharacterId(String ch, Map<String, Object> claims);

    boolean existById(String id);

    boolean save(Characters character);

    boolean editCharacter(String id, Characters characters);
}
