package ru.zinkin.app.marvel_superheroes_card.util;

import org.springframework.stereotype.Component;
import ru.zinkin.app.marvel_superheroes_card.model.dto.request.RequestComicsDto;
import ru.zinkin.app.marvel_superheroes_card.model.pojo.Comics;
import ru.zinkin.app.marvel_superheroes_card.util.abstracts.ConverterUtil;

import java.util.ArrayList;

@Component
public class ComicsConverter implements ConverterUtil<Comics,RequestComicsDto> {
    @Override
    public Comics converted(RequestComicsDto requestComicsDto) {
        return new Comics(
                requestComicsDto.getId(),
                requestComicsDto.getName(),
                requestComicsDto.getDescription(),
                requestComicsDto.getPublished(),
                "standart_comics.png",
                requestComicsDto.getWriters(),
                requestComicsDto.getPencilers(),
                requestComicsDto.getCover_artist(),
                new ArrayList<>());
    }
}
