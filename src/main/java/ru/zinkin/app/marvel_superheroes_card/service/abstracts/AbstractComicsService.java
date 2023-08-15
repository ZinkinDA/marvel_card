package ru.zinkin.app.marvel_superheroes_card.service.abstracts;

import org.springframework.data.domain.Page;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Characters;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;

import java.util.Map;
import java.util.Optional;

public interface AbstractComicsService extends AbstractComicsDtoService {
    Page<Comics> getComicsAll(Map<String, Object> claims);

    Optional<Comics> getComicsById(String id);

    Page<Characters> getCharacterByComicsId(String id, Map<String, Object> claims);

    Boolean existById(String id);

    boolean saveComics(Comics comics);

    boolean editComics(Comics comment);
}
